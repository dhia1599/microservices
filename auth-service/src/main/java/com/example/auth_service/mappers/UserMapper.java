package com.example.auth_service.mappers;

import com.example.auth_service.dtos.CreatedUserDto;
import com.example.auth_service.dtos.UserDto;
import com.example.auth_service.entities.UserEntity;

public class UserMapper {

    public static UserDto toDto(UserEntity user) {
        return new UserDto(user.getId(), user.getUsername(), user.getRole());
    }

    public static UserEntity toEntity(CreatedUserDto createdUserDto) {
        return new UserEntity(null, createdUserDto.getUsername(), createdUserDto.getPassword(), createdUserDto.getRole());
    }
}
