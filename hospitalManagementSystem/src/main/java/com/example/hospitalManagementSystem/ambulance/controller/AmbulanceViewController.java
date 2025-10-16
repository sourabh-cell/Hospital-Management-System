package com.example.hospitalManagementSystem.ambulance.controller;

import com.example.hospitalManagementSystem.ambulance.entity.Ambulance;
import com.example.hospitalManagementSystem.ambulance.service.AmbulanceAssignmentService;
import com.example.hospitalManagementSystem.ambulance.service.AmbulanceService;
import com.example.hospitalManagementSystem.ambulance.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AmbulanceViewController {

    @Autowired
    private AmbulanceService ambulanceService;

    @Autowired
    private DriverService driverService;

    @Autowired
    AmbulanceAssignmentService ambulanceAssignmentService;

    @GetMapping("/ambulance/view")
    public String viewAmbulance() {
        return "index";
    }



    @GetMapping("/ambulance/list")
    public String ambulanceList(Model model)
    {
        model.addAttribute("ambulance",ambulanceService.findAllAmbulanceFullObject());
        System.out.println("inside ambulance view");
        return "ambulance/fragments/ambulance-table :: ambulanceTable";
    }


    @GetMapping("/driver/list")
    public String driverList(Model model)
    {
        model.addAttribute("driver",driverService.findAllDriverFullObject());

        return "ambulance/fragments/driver-table";
    }


    @GetMapping("/assignment/list")
    public String assignmentList(Model model)
    {
        model.addAttribute("assignment",ambulanceAssignmentService.findAllInProgressAmbulanceAssignments());

        return "ambulance/fragments/assignment-table";
    }

    @GetMapping("/assignment/history")
    public String assignmentCompleted(Model model)
    {
        model.addAttribute("assignment",ambulanceAssignmentService.findAllCompletedAmbulanceAssignments());

        return "ambulance/fragments/assignment-history-table";
    }


}
