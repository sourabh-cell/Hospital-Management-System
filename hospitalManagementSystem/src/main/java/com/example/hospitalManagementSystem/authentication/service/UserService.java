package com.example.hospitalManagementSystem.authentication.service;

import com.example.hospitalManagementSystem.authentication.entity.UserEntity;

import java.util.Optional;

public interface UserService {

    UserEntity createUser(UserEntity userEntity);

    Optional<UserEntity> findUserByUsername(String username);



}
