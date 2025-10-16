package com.example.hospitalManagementSystem.ambulance.controller;

import com.example.hospitalManagementSystem.ambulance.dto.DriverDTO;
import com.example.hospitalManagementSystem.ambulance.repo.AmbulanceRepo;
import com.example.hospitalManagementSystem.ambulance.repo.DriverRepo;
import com.example.hospitalManagementSystem.ambulance.service.AmbulanceService;
import com.example.hospitalManagementSystem.ambulance.service.DriverService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DriverController {



    @Autowired
    DriverService driverService;
    @Autowired
    private AmbulanceService ambulanceService;

    @GetMapping("/driver/add")
    @PreAuthorize("hasAuthority('DRIVER_ADD')")
    public String showAddDriver()
    {
        return "index";
    }

    @PostMapping("/driver/add")
    @PreAuthorize("hasAuthority('DRIVER_ADD')")
    public String addDriver(@Valid @ModelAttribute("driverDto") DriverDTO dto,
                            BindingResult result,
                            Model model,
                            RedirectAttributes redirectAttributes
                            )
    {
        if (result.hasErrors())
        {
            model.addAttribute("ambulanceList", ambulanceService.findAllAmbulance());
            return "ambulance/driver-add";
        }
        try {
            driverService.saveDriver(dto);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("ambulanceList", ambulanceService.findAllAmbulance());
            model.addAttribute("error", "Driver with this License Number already exists!");
            return "ambulance/driver-add";
        }

        redirectAttributes.addFlashAttribute("success", "Driver Added Successfully!");
        return "redirect:/driver/add";
    }
}
