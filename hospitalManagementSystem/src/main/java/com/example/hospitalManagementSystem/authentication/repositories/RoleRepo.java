package com.example.hospitalManagementSystem.authentication.repositories;

import com.example.hospitalManagementSystem.authentication.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Long> {
}
