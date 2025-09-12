package com.example.hospitalManagementSystem.authentication.service;


import com.example.hospitalManagementSystem.authentication.entity.UserEntity;
import com.example.hospitalManagementSystem.authentication.service.serviceImplementation.UserServiceImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;


    //injecting through constructor
    public CustomUserDetailsService(UserServiceImpl userService) {
        this.userService = userService;

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userService.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        // ✅ Convert roles into GrantedAuthority
        Set<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName())) // ROLE_ADMIN, ROLE_SUPER_ADMIN, etc.
                .collect(Collectors.toSet());

        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
