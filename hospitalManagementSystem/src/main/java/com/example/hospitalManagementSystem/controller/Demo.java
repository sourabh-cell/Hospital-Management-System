package com.example.hospitalManagementSystem.controller;

import com.example.hospitalManagementSystem.entity.User;
import com.example.hospitalManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class Demo {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String index()
    {
        return "dashboard-main";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User()); // this "user" is used by th:object
        return "registration_form"; // Thymeleaf template name
    }

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@ModelAttribute("user") User user) {
        User savedUser = userService.createUser(user);
        System.out.println("User created successfully");
        return ResponseEntity.ok(savedUser);
    }
}
