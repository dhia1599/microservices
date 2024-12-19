package com.Gateway.filters;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.text.CompactNumberFormat;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    private final WebClient.Builder webClient;

    public AuthFilter(WebClient.Builder webClient) {
        super(Config.class);
        this.webClient = webClient;
    }

    public static class Config {
        // You can define filter-specific configuration fields here if needed.
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            HttpHeaders headers = exchange.getRequest().getHeaders();
            String authHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            String token = authHeader.substring(7);

            return webClient.build()
                    .get()
                    .uri("http://auth-service/api/auth/validate?token=" + token)
                    .retrieve()
                    .onStatus(status -> status.isError(), clientResponse -> {
                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                        return Mono.error(new RuntimeException("Error validating token"));
                    })
                    .bodyToMono(Boolean.class)
                    .flatMap(isValid -> {
                        if (Boolean.TRUE.equals(isValid)) {
                            // Token is valid, proceed to the next filter
                            return chain.filter(exchange);
                        } else {
                            // Token is invalid
                            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                            return exchange.getResponse().setComplete();
                        }
                    });
        };
    }
}
