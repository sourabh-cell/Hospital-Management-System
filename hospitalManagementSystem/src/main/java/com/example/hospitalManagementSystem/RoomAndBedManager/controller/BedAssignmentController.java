package com.example.hospitalManagementSystem.RoomAndBedManager.controller;

import com.example.hospitalManagementSystem.RoomAndBedManager.dto.BedAssignmentDTO;
import com.example.hospitalManagementSystem.RoomAndBedManager.dto.RoomDTO;
import com.example.hospitalManagementSystem.RoomAndBedManager.service.BedAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

@Controller
public class BedAssignmentController {

    @Autowired
    BedAssignmentService bedAssignmentService;

    @GetMapping("/bed/list")
    @PreAuthorize("hasAuthority('BED_LIST')")
    public String showBedList(Model model)
    {
      model.addAttribute("room",bedAssignmentService.findAllTotalVaccantBedsByRoom());

      return "bed-list";
    }

    @GetMapping("/assign-bed/{id}")
    public String showAssignBed(@PathVariable Long id, Model model)
    {
        if (!bedAssignmentService.checkingVacantBedInRoom(id)) {
            // if no vacant beds
            model.addAttribute("error", "No vacant beds available in this room!");
            return "redirect:/bed/list";  // back to list page
        }

            model.addAttribute("bedNumber", bedAssignmentService.showAllAvailableBedNumbers(id));
            model.addAttribute("patientIds", bedAssignmentService.findAllPatientId());
            model.addAttribute("room", bedAssignmentService.showRoomDTOById(id));
            model.addAttribute("bedAssignmentDTO", new BedAssignmentDTO());
       return "bed-assign";
    }

    @PostMapping("/bed/assignment")
    @PreAuthorize("hasAuthority('BED_ASSIGN')")
    public String addBedAssignment(@ModelAttribute BedAssignmentDTO bedAssignmentDTO, RedirectAttributes redirectAttributes)
    {
        try {

            bedAssignmentService.saveBedAssignment(bedAssignmentDTO);
            redirectAttributes.addFlashAttribute("success", "Bed assigned successfully!");
            return "redirect:/allocated/beds";
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/bed/list";
    }

    @GetMapping("/allocated/beds")
    public String  showAllocatedBeds(Model model)
    {
        model.addAttribute("bedAllotedDTO", bedAssignmentService.showAllotedBed());
        return  "allocated-beds";
    }

    @GetMapping("/bed/release{bedAssignmentId}")
    public String releaseBed(@PathVariable Long bedAssignmentId)
    {
        bedAssignmentService.releaseBed(bedAssignmentId);
        return  "redirect:/allocated/beds";
    }
}
