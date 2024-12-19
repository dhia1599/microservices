package com.example.auth_service.services;

import com.example.auth_service.dtos.CreatedUserDto;
import com.example.auth_service.dtos.UserDto;
import com.example.auth_service.entities.UserEntity;
import com.example.auth_service.mappers.UserMapper;
import com.example.auth_service.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<UserDto> getAll() {
        return userRepo.findAll()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> getById(Long id) {
        return userRepo.findById(id)
                .map(UserMapper::toDto);
    }

    @Override
    public Optional<UserDto> getByUsername(String username) {
        return this.userRepo.findByUsername(username)
                .map(UserMapper::toDto);
    }

    @Override
    public UserDto create(CreatedUserDto createdUser) {
        UserEntity userEntity = UserMapper.toEntity(createdUser);
        UserEntity savedUser = userRepo.save(userEntity);
        return UserMapper.toDto(savedUser);
    }

    @Override
    public List<UserDto> createMultipleUsers(List<CreatedUserDto> list) {
        List<UserEntity> entities = list.stream()
                .map(UserMapper::toEntity)
                .collect(Collectors.toList());
        List<UserEntity> savedEntities = userRepo.saveAll(entities);
        return savedEntities.stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }


}
