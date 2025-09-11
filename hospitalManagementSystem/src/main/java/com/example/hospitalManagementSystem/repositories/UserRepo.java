package com.example.hospitalManagementSystem.repositories;

import com.example.hospitalManagementSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {


}
