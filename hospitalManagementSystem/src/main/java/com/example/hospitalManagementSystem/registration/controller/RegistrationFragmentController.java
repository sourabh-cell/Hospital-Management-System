package com.example.hospitalManagementSystem.registration.controller;

import com.example.hospitalManagementSystem.accountant.dto.AccountantDto;
import com.example.hospitalManagementSystem.authentication.entity.Role;
import com.example.hospitalManagementSystem.authentication.repositories.RoleRepo;
import com.example.hospitalManagementSystem.authentication.repositories.UserRepo;
import com.example.hospitalManagementSystem.department.repo.DepartmentRepo;
import com.example.hospitalManagementSystem.doctor.dto.DoctorDto;
import com.example.hospitalManagementSystem.enums.Enums;
import com.example.hospitalManagementSystem.head_nurse.dto.HeadNurseDto;
import com.example.hospitalManagementSystem.hr.dto.HumanResourceDto;
import com.example.hospitalManagementSystem.insurance.dto.InsurerDto;
import com.example.hospitalManagementSystem.laboratory.dto.LaboratoristDto;
import com.example.hospitalManagementSystem.pharmacy.dto.PharmacistDto;
import com.example.hospitalManagementSystem.receptionist.dto.ReceptionistDto;
import com.example.hospitalManagementSystem.registration.dto.RegistrationDto;
import com.example.hospitalManagementSystem.registration.dto.RegistrationDtoValidator;
import com.example.hospitalManagementSystem.registration.service.RegistrationService;
import com.example.hospitalManagementSystem.registration.service.RegistrationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class RegistrationFragmentController {


        private final RegistrationService registrationService;
        private final RoleRepo roleRepo;
        private final DepartmentRepo departmentRepo;
        private final UserRepo userRepo;
        private static final Logger log = LoggerFactory.getLogger(RegistrationServiceImpl.class);


        private final RegistrationDtoValidator registrationDtoValidator;

        @InitBinder("registrationDto")
        protected void initBinder(WebDataBinder binder) {
            binder.addValidators(registrationDtoValidator);
        }


        @GetMapping("/fragment/register")
        public String showRegistrationForm(@ModelAttribute("registrationDto") RegistrationDto dto, @RequestParam(value = "success", required = false) String success, Model model) {

            List<Role> allRoles = roleRepo.findAll();
            List<Role> filteredRoles = allRoles.stream()
                    .filter(role -> !role.getRoleName().equals("ROLE_SUPER_ADMIN") && !role.getRoleName().equals("ROLE_ADMIN"))
                    .map(role -> {
                        Role displayRole = new Role();
                        displayRole.setRoleId(role.getRoleId());
                        displayRole.setRoleName(role.getRoleName().replace("ROLE_", "")); // Remove prefix
                        return displayRole;
                    })
                    .toList();


//        model.addAttribute("registrationDto", new RegistrationDto());

            // Inject roles and experience options
            if (success != null) {
                model.addAttribute("successMessage", "Registration completed successfully!");
            }


            model.addAttribute("roles", filteredRoles);
            model.addAttribute("departments", departmentRepo.findAll());
            model.addAttribute("experienceOptions", Enums.ExperienceLevel.values());

            return "registration-form :: registrationFormFragment"; //
        }


}
