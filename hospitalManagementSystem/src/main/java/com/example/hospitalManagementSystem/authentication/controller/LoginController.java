package com.example.hospitalManagementSystem.authentication.controller;

import com.example.hospitalManagementSystem.authentication.entity.UserEntity;
import com.example.hospitalManagementSystem.authentication.service.CurrentUserAndPermissionService;
import com.example.hospitalManagementSystem.authentication.service.UserService;
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
    private UserService userService;

    @Autowired
    private CurrentUserAndPermissionService currentUserAndPermissionService;

    @GetMapping("/")
    public String showHome()
    {

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLogin()
    {

        return "login";
    }

    @GetMapping("/super_admin")
    public String showAdmin(Model model)
    {
            return "index";
    }






    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserEntity()); // this "user" is used by th:object
        return "registration-form"; // Thymeleaf template name
    }

    @PostMapping("/register")
    public ResponseEntity<UserEntity> createUser(@ModelAttribute("user") UserEntity userEntity) {
        UserEntity savedUserEntity = userService.createUser(userEntity);
        System.out.println("User created successfully");
        return ResponseEntity.ok(savedUserEntity);
    }
}
