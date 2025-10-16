package com.example.hospitalManagementSystem.ambulance.controller;

import com.example.hospitalManagementSystem.ambulance.dto.AmbulanceAssignmentDTO;
import com.example.hospitalManagementSystem.ambulance.service.AmbulanceAssignmentService;
import com.example.hospitalManagementSystem.ambulance.service.AmbulanceService;
import com.example.hospitalManagementSystem.ambulance.service.DriverService;
import com.example.hospitalManagementSystem.doctor.service.PatientService;
import com.example.hospitalManagementSystem.enums.AmbulanceEnums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AmbulanceAssignmentFragmentController {

    @Autowired
    private AmbulanceAssignmentService ambulanceAssignmentService;

    @Autowired
    AmbulanceService ambulanceService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private PatientService patientService;

    @GetMapping("/fragment/ambulance/assignment")
    @PreAuthorize("hasAuthority('AMBULANCE_ASSIGNMENT')")
    public String showAmbulanceAssignmentFragment(Model model) {
        model.addAttribute("driverList",driverService.findAllDriver());
        model.addAttribute("ambulanceList",ambulanceService.findAllAmbulance());
        model.addAttribute("assignmentStatus", AmbulanceEnums.AssignmentStatus.values());
        model.addAttribute("ambulanceAssignmentDto",new AmbulanceAssignmentDTO());
        return "ambulance/ambulance-assignment :: ambulanceAssignmentFragment";
    }
}
