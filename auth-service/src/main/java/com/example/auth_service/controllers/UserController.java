package com.example.auth_service.controllers;

import com.example.auth_service.dtos.CreatedUserDto;
import com.example.auth_service.dtos.UserDto;
import com.example.auth_service.entities.UserEntity;
import com.example.auth_service.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAll();
        return ResponseEntity.ok(users);
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        Optional<UserDto> user = userService.getById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        Optional<UserDto> user = userService.getByUsername(username);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new user
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody CreatedUserDto user) {
        UserDto createdUser = userService.create(user);
        return ResponseEntity.ok(createdUser);
    }

    // Create multiple users
    @PostMapping("/bulk")
    public ResponseEntity<List<UserDto>> createMultipleUsers(@RequestBody List<CreatedUserDto> users) {
        List<UserDto> createdUsers = userService.createMultipleUsers(users);
        return ResponseEntity.ok(createdUsers);
    }
}
