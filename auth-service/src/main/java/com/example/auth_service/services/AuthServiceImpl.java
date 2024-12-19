package com.example.auth_service.services;

import com.example.auth_service.dtos.UserDto;
import com.example.auth_service.entities.UserEntity;
import com.example.auth_service.mappers.UserMapper;
import com.example.auth_service.repositories.UserRepo;
import com.example.auth_service.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private final UserRepo userRepo;

    @Autowired
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserRepo userRepo, JwtUtil jwtUtil) {
        this.userRepo = userRepo;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String authenticate(String username, String password) {
        Optional<UserEntity> user = userRepo.findByUsername(username);

        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return jwtUtil.createToken(user.get().getUsername(), user.get().getRole());
        } else {
            throw new RuntimeException("Invalid username or password !");
        }
    }

    @Override
    public UserDto findUserByUsername(String username) {
        Optional<UserEntity> user = userRepo.findByUsername(username);
        return user.map(UserMapper::toDto)
                .orElseThrow(() -> new RuntimeException("User not found !"));
    }

    @Override
    public String generateToken(String username, String role) {
        return jwtUtil.createToken(username, role);
    }

    @Override
    public boolean validateToken(String token) {
        try {
            jwtUtil.validateToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
