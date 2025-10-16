package com.example.hospitalManagementSystem.RoomAndBedManager.controller;

import com.example.hospitalManagementSystem.RoomAndBedManager.dto.BedDTO;
import com.example.hospitalManagementSystem.RoomAndBedManager.dto.CreateRoomDTO;
import com.example.hospitalManagementSystem.RoomAndBedManager.entity.Bed;
import com.example.hospitalManagementSystem.RoomAndBedManager.repo.BedRepo;
import com.example.hospitalManagementSystem.RoomAndBedManager.service.BedService;
import com.example.hospitalManagementSystem.enums.Enums;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BedController {

    @Autowired
    BedService bedService;


    @GetMapping("/bed/add")
    @PreAuthorize("hasAuthority('BED_ADD')")
    public String showAddBed(Model model)
    {
        model.addAttribute("rooms", bedService.getAllRoomsName());
        model.addAttribute("bedDTO", new BedDTO());
        model.addAttribute("status", Enums.BedStatus.values());
        return "bed-add";
    }

    @PostMapping("bed/add")
    @PreAuthorize("hasAuthority('BED_ADD')")
    public String saveBed(@Valid @ModelAttribute BedDTO bedDTO,
                          BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("rooms", bedService.getAllRoomsName());
            model.addAttribute("status", Enums.BedStatus.values());
            return "bed-add"; // show the same form again with error messages
        }

        bedService.saveBed(bedDTO);
        model.addAttribute("success", "Bed added successfully!");
        return "redirect:/bed/list";
    }
}
