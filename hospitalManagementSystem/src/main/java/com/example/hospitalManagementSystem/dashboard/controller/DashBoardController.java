package com.example.hospitalManagementSystem.dashboard.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class DashBoardController {

    @GetMapping("/dashboard/admin")
    public String adminDashboard() {
        return "index";
    }
}
