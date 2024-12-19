package com.example.auth_service.services;

import com.example.auth_service.dtos.UserDto;

public interface AuthService {

    String authenticate(String username, String password);

    UserDto findUserByUsername(String username);

    String generateToken(String username, String role);

    boolean validateToken(String token);
}
