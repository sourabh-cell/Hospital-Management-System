package com.example.hospitalManagementSystem.ambulance.controller;

import com.example.hospitalManagementSystem.ambulance.dto.DriverDTO;
import com.example.hospitalManagementSystem.ambulance.service.AmbulanceService;
import com.example.hospitalManagementSystem.ambulance.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DriverFragmentController {


    @Autowired
    DriverService driverService;
    @Autowired
    private AmbulanceService ambulanceService;

    @GetMapping("/fragment/driver/add")
    @PreAuthorize("hasAuthority('DRIVER_ADD')")
    public String driverAdd(Model model) {
        model.addAttribute("ambulanceList", ambulanceService.findAllAmbulance());
        model.addAttribute("driverDto", new DriverDTO());
        return "ambulance/driver-add :: driverAddFragment";
    }


}
