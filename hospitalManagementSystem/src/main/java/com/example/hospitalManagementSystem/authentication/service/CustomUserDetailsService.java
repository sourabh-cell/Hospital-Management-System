package com.example.hospitalManagementSystem.authentication.service;


import com.example.hospitalManagementSystem.authentication.entity.CustomUserDetails;
import com.example.hospitalManagementSystem.authentication.entity.Permission;
import com.example.hospitalManagementSystem.authentication.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    private final PermissionService permissionService;


    //injecting through constructor
    public CustomUserDetailsService(UserServiceImpl userService, PermissionService permissionService) {
        this.userService = userService;
        this.permissionService = permissionService;

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // (1) Find user by username
        UserEntity user = userService.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        // (2) Get permissions for this user
        List<Permission> permissions = permissionService.getUserPermissionsByUserId(user.getUserId());

        // (3) Wrap user and permissions inside CustomUserDetails
        return new CustomUserDetails(user, permissions);
    }
}
