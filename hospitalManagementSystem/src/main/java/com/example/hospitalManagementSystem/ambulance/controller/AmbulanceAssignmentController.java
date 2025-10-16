package com.example.hospitalManagementSystem.ambulance.controller;

import com.example.hospitalManagementSystem.ambulance.dto.AmbulanceAssignmentDTO;
import com.example.hospitalManagementSystem.ambulance.service.AmbulanceAssignmentService;
import com.example.hospitalManagementSystem.ambulance.service.AmbulanceService;
import com.example.hospitalManagementSystem.ambulance.service.DriverService;
import com.example.hospitalManagementSystem.authentication.service.UserService;
import com.example.hospitalManagementSystem.doctor.service.PatientService;
import com.example.hospitalManagementSystem.enums.AmbulanceEnums;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AmbulanceAssignmentController {

    @Autowired
    private AmbulanceAssignmentService ambulanceAssignmentService;

    @Autowired
    AmbulanceService  ambulanceService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private PatientService patientService;

    @GetMapping("/ambulance/assignment/add")
    @PreAuthorize("hasAuthority('AMBULANCE_ASSIGNMENT')")
    public  String showAmbulanceAssignment(Model model)
    {
        return "index";
    }

    @PostMapping ("/ambulance/assignment/add")
    @PreAuthorize("hasAuthority('AMBULANCE_ASSIGNMENT')")
    public  String addAmbulanceAssignment(@Valid @ModelAttribute("ambulanceAssignmentDto")
                                              AmbulanceAssignmentDTO ambulanceAssignmentDTO,
                                              BindingResult result,
                                              RedirectAttributes redirectAttributes,
                                              Model model)

    {


        if (result.hasErrors()) {
            System.out.println(result.getAllErrors().toString());
            model.addAttribute("driverList",driverService.findAllDriver());
            model.addAttribute("assignmentStatus", AmbulanceEnums.AssignmentStatus.values());
            model.addAttribute("ambulanceList",ambulanceService.findAllAmbulance());
            return "ambulance/ambulance-assignment";

        }

        try {
            ambulanceAssignmentService.saveAmbulanceAssignment(ambulanceAssignmentDTO);
            System.out.println("ambulance assignment controller");

        }
        catch (DataIntegrityViolationException e) {
            model.addAttribute("driverList",driverService.findAllDriver());
            model.addAttribute("ambulanceList",ambulanceService.findAllAmbulance());
            model.addAttribute("assignmentStatus", AmbulanceEnums.AssignmentStatus.values());
            model.addAttribute("error","Please enter Valid data");
            return "ambulance/ambulance-assignment";
        }


        redirectAttributes.addFlashAttribute("success", "Ambulance Trip Assign Successfully!");
        return "redirect:/ambulance/assignment/add";
    }


    @PostMapping("/assignments/changeStatus/{id}")
    public String changeAssignmentStatus(@PathVariable Long id,
                                         @RequestParam String status,
                                         RedirectAttributes redirectAttributes)
    {
        ambulanceAssignmentService.updateAssignmentStatus(id,status);
        redirectAttributes.addFlashAttribute("success", "Assignment Status Changed Successfully!");
        return "redirect:/ambulance/view";
    }
}
