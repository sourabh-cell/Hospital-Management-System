package com.example.hospitalManagementSystem.RoomAndBedManager.controller;

import com.example.hospitalManagementSystem.RoomAndBedManager.dto.CreateRoomDTO;
import com.example.hospitalManagementSystem.RoomAndBedManager.service.RoomService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RoomController {

    @Autowired
    RoomService roomService;

    @GetMapping("/room/add")
    @PreAuthorize("hasAuthority('ROOM_ADD')")
    public String showRoom(Model model)
    {
        model.addAttribute("createRoomDto", new CreateRoomDTO());
        model.addAttribute("roomStatus", Enums.RoomStatus.values());
        model.addAttribute("roomType",roomService.findAllAvailaibleRoomTypes());
        return "add-rooms";
    }

    @PostMapping("/room/add")
    @PreAuthorize("hasAuthority('ROOM_ADD')")
    public String saveRoom(@Valid @ModelAttribute("createRoomDto") CreateRoomDTO dto,
                                           BindingResult result,Model model, RedirectAttributes redirectAttributes)
    {
        if (result.hasErrors()) {

            model.addAttribute("roomStatus", Enums.RoomStatus.values());
            model.addAttribute("roomType",roomService.findAllAvailaibleRoomTypes());
            return "add-rooms";
        }

        roomService.createRoom(dto);
        redirectAttributes.addFlashAttribute("success", "Room created successfully!");
        return "redirect:/bed/list";
    }
}
