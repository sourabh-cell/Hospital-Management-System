package com.example.hospitalManagementSystem.dashboard.dashboard_controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class DashBoardController {


    @GetMapping("/dashboard/{role}")
    public String loadDashboard(@PathVariable String role) {
        System.out.println("inside dashboard controller");
        List<String> allowedRoles = List.of("super_admin","admin", "doctor", "pharmacist", "hr", "accountant", "receptionist", "headnurse", "laboratorist", "insurance");

        if (!allowedRoles.contains(role)) {
            return "redirect:/dashboard/default";
        }

        return "index";
    }

}
