package com.example.hospitalManagementSystem.dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashBoardFragmentController {

    @GetMapping("fragment/dashboard/admin")
    public String adminDashboard() {

        return "dashboard/admin-Dashboard :: admin-Dashboard";
    }
}
