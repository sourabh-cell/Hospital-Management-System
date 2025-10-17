package com.example.hospitalManagementSystem.dashboard.fragment_controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FragmentController {

    @GetMapping("fragment/dashboard/admin")
    public String adminDashboard() {

        return "dashboard/admin-Dashboard :: admin-Dashboard";
    }

    @GetMapping("fragment/dashboard/doctor")
    public String doctorDashboard() {
        return "dashboard/doctor-dashboard :: doctor-dashboard";
    }

    @GetMapping("fragment/dashboard/default")
    public String defaultDashboard() {
        return "dashboard/unauthorized-dashboard :: unauthorized-dashboard";

    }
}
