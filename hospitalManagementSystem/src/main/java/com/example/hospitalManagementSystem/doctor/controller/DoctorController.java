package com.example.hospitalManagementSystem.doctor.controller;

import com.example.hospitalManagementSystem.authentication.service.CurrentUserAndPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class DoctorController {

    @Autowired
    CurrentUserAndPermissionService currentUserAndPermissionService;


    @GetMapping("/doctor")
    public String doctorPage(Model model) {
        System.out.println("doctor controller");
        model.addAttribute("user",currentUserAndPermissionService.getCurrentUser());
        model.addAttribute("permissions",currentUserAndPermissionService.getPermissions());
        return "index"; // doctor.html
    }


    @GetMapping("/doctor/add")
    @PreAuthorize("hasAuthority('DOCTOR_ADD')")
    public String addDoctor()
    {
        return "doctor";
    }


}
