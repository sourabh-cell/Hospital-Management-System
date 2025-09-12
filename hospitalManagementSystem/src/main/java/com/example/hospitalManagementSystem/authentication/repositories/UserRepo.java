package com.example.hospitalManagementSystem.authentication.repositories;

import com.example.hospitalManagementSystem.authentication.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findByUsername(String username);
}
