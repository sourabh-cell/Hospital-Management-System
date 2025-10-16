package com.example.hospitalManagementSystem.ambulance.controller;

import com.example.hospitalManagementSystem.ambulance.service.AmbulanceAssignmentService;
import com.example.hospitalManagementSystem.ambulance.service.AmbulanceService;
import com.example.hospitalManagementSystem.ambulance.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AmbulanceViewFragmentController {

    @Autowired
    private AmbulanceService ambulanceService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private AmbulanceAssignmentService ambulanceAssignmentService;

    @GetMapping("/fragment/ambulance/view")
    public String viewAmbulanceFragment(Model model) {
        model.addAttribute("ambulances", ambulanceService.findAllAmbulanceFullObject());
        model.addAttribute("drivers", driverService.findAllDriverFullObject());
        model.addAttribute("assignments", ambulanceAssignmentService.findAllInProgressAmbulanceAssignments());
        model.addAttribute("completedAssignments", ambulanceAssignmentService.findAllCompletedAmbulanceAssignments());
        return "ambulance/ambulance-view :: ambulance-view";
    }
}
