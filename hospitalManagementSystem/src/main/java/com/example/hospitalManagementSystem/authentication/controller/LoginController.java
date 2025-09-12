package com.example.hospitalManagementSystem.authentication.controller;

import com.example.hospitalManagementSystem.authentication.entity.UserEntity;
import com.example.hospitalManagementSystem.authentication.service.serviceImplementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/")
    public String showHome()
    {

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLogin()
    {

        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserEntity()); // this "user" is used by th:object
        return "registration_form"; // Thymeleaf template name
    }

    @PostMapping("/register")
    public ResponseEntity<UserEntity> createUser(@ModelAttribute("user") UserEntity userEntity) {
        UserEntity savedUserEntity = userServiceImpl.createUser(userEntity);
        System.out.println("User created successfully");
        return ResponseEntity.ok(savedUserEntity);
    }
}
