package com.example.hospitalManagementSystem.ambulance.controller;

import com.example.hospitalManagementSystem.ambulance.dto.AmbulanceDTO;
import com.example.hospitalManagementSystem.enums.AmbulanceEnums;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AmbulanceFragmentController {

    @GetMapping("/fragment/ambulance/add")
    public String showAddAmbulanceFragment(Model model)
    {
        model.addAttribute("ambulanceStatus", AmbulanceEnums.AmbulanceStatus.values());
        model.addAttribute("ambulanceType", AmbulanceEnums.AmbulanceType.values());
        model.addAttribute("ambulanceDto", new AmbulanceDTO());
        return "ambulance/ambulance-add :: ambulanceAddFragment";
    }

}
