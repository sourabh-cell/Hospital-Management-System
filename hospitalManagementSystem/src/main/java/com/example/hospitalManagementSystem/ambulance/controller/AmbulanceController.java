package com.example.hospitalManagementSystem.ambulance.controller;

import com.example.hospitalManagementSystem.ambulance.dto.AmbulanceDTO;
import com.example.hospitalManagementSystem.ambulance.service.AmbulanceService;
import com.example.hospitalManagementSystem.enums.AmbulanceEnums;
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
public class AmbulanceController {

    @Autowired
    AmbulanceService ambulanceService;

    @GetMapping("/ambulance/add")
    @PreAuthorize("hasAuthority('AMBULANCE_ADD')")
    public String showAddAmbulance()
    {
        return "index";
    }


    @PostMapping("/ambulance/add")
    @PreAuthorize("hasAuthority('AMBULANCE_ADD')")
    public String addAmbulance(@Valid @ModelAttribute("ambulanceDto") AmbulanceDTO ambulanceDTO,
                               BindingResult result,
                               Model model) {

        // Populate dropdowns for form
        model.addAttribute("ambulanceStatus", AmbulanceEnums.AmbulanceStatus.values());
        model.addAttribute("ambulanceType", AmbulanceEnums.AmbulanceType.values());

        // If validation errors, return fragment
        if (result.hasErrors()) {
            return "ambulance/ambulance-add :: ambulanceAddFragment";
        }

        try {
            ambulanceService.saveAmbulance(ambulanceDTO);
            model.addAttribute("success", "Ambulance added successfully!");
            // Clear form by sending a new DTO
            model.addAttribute("ambulanceDto", new AmbulanceDTO());
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "Ambulance with same Vehicle Number Already Exists");
        }

        // Always return fragment (success or error)
        return "ambulance/ambulance-add :: ambulanceAddFragment";
    }



}
