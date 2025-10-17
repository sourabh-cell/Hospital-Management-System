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
    public String showHome() {

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLogin() {

        return "login";
    }


}
