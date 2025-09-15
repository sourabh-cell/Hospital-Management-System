package com.example.hospitalManagementSystem.authentication.repositories;

import com.example.hospitalManagementSystem.authentication.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findByUsername(String username);

    // Check if a user has a specific role

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END " +
            "FROM UserEntity u JOIN u.roles r " +
            "WHERE u.userId = :userId AND r.roleName = :roleName")
    boolean hasRole(@Param("userId") Long userId, @Param("roleName") String roleName);
}
