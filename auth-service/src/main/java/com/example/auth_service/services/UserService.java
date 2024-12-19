package com.example.auth_service.services;

import com.example.auth_service.dtos.CreatedUserDto;
import com.example.auth_service.dtos.UserDto;
import com.example.auth_service.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> getAll();

    Optional<UserDto> getById(Long id);

    Optional<UserDto> getByUsername(String username);

    UserDto create(CreatedUserDto user);

    List<UserDto> createMultipleUsers(List<CreatedUserDto> list);
}
