package com.example.hospitalManagementSystem.service;

import com.example.hospitalManagementSystem.entity.User;
import com.example.hospitalManagementSystem.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo  userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User createUser(User user)
    {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return userRepo.save(user);


    }
}
