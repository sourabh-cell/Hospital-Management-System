package com.example.hospitalManagementSystem.authentication.service;

import com.example.hospitalManagementSystem.authentication.service.serviceImplementation.UserServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserServiceImpl userServiceImpl;


    //injecting through constructor
    public CustomUserDetailsService(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       return userServiceImpl.findUserByUsername(username)
               .map(user -> User.builder()
                       .username(user.getUsername())
                       .password(user.getPassword()) // BCrypt password
                       .roles(user.getRoles().toArray(new String[0])) // ["ADMIN", "DOCTOR", etc.]
                       .disabled(!user.isEnabled()) // invert because builder takes "disabled"
                       .build()
               )
               .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}
