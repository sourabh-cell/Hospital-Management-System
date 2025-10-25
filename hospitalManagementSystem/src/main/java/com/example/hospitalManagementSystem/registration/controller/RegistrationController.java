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
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;
    private final RoleRepo roleRepo;
    private final DepartmentRepo  departmentRepo;
    private final UserRepo userRepo;
    private static final Logger log = LoggerFactory.getLogger(RegistrationServiceImpl.class);


private final RegistrationDtoValidator registrationDtoValidator;

    @InitBinder("registrationDto")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(registrationDtoValidator);
    }


    @GetMapping("/register")
    public String showRegistrationForm()
    {
        return "index";
    }

//    ------------------------------------------------------------------------------------------




//    ------------------------------------------------------------------------------------------

    @PostMapping("/register")
    public String registerUser( @ModelAttribute("registrationDto") RegistrationDto dto,
                               BindingResult result,
                               Model model)
    {

        initializeRoleDto(dto);
        registrationDtoValidator.validate(dto, result);
// Check for duplicate username
        if (userRepo.existsByUsername(dto.getUsername())) {
            result.rejectValue("username", "error.username", "Username already exists");
        }

        // Check for duplicate email
        if (userRepo.existsByEmail(dto.getEmail())) {
            result.rejectValue("email", "error.email", "Email already exists");
        }

        // Password match check
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            result.rejectValue("confirmPassword", "error.confirmPassword", "Passwords do not match");
        }


        // If validation fails, reinitialize role DTO and return form
        if (result.hasErrors()) {
            log.info("inside error");
            result.getFieldErrors().forEach(e ->
                    log.warn("Field error: {} -> {}", e.getField(), e.getDefaultMessage()));

//            Before you added role-specific validation, you weren’t calling initializeRoleDto(dto) or modifying the DTO after validation. So the original registrationDto and its BindingResult stayed intact.
//            Now, after adding: initializeRoleDto(dto);
//            and nested validation logic, if you re-add registrationDto to the model after validation, Spring may treat it as a new object, and the original errors won’t be linked anymore.
//              That is why dont add the below line.
//            model.addAttribute("registrationDto", dto);
            model.addAttribute("roles", roleRepo.findAll());
            model.addAttribute("departments", departmentRepo.findAll());
            model.addAttribute("experienceOptions", Enums.ExperienceLevel.values());
            log.info("Validation errors: {}", result.getAllErrors());
            return "registration-form :: registrationFormFragment";

        }





        try {
            log.info("inside try block");
            registrationService.registerUser(dto);
        } catch (IllegalArgumentException | IOException ex) {
            log.info("inside catch block");

            model.addAttribute("registrationDto", dto);
            model.addAttribute("roles", roleRepo.findAll());
            model.addAttribute("departments", departmentRepo.findAll());
            model.addAttribute("experienceOptions", Enums.ExperienceLevel.values());
            model.addAttribute("errorMessage", ex.getMessage());

            return "registration-form :: registrationFormFragment";
        }

        model.addAttribute("successMsg","Registration Successful!");
        return "registration-form :: registrationFormFragment";

    }


//    ------------------------------------------------------------------------------------------
//    Helper Method

private void initializeRoleDto(RegistrationDto dto) {
    if (dto.getRoleId() == null) return;

    switch (dto.getRoleId().intValue()) {
        case 3:
            if (dto.getDoctorDto() == null) dto.setDoctorDto(new DoctorDto());
            break;
        case 4:
            if (dto.getHeadNurseDto() == null) dto.setHeadNurseDto(new HeadNurseDto());
            break;
        case 5:
            if (dto.getPharmacistDto() == null) dto.setPharmacistDto(new PharmacistDto());
            break;
        case 6:
            if (dto.getAccountantDto() == null) dto.setAccountantDto(new AccountantDto());
            break;
        case 7:
            if (dto.getHumanResourceDto() == null) dto.setHumanResourceDto(new HumanResourceDto());
            break;
        case 8:
            if (dto.getLaboratoristDto() == null) dto.setLaboratoristDto(new LaboratoristDto());
            break;
        case 9:
            if (dto.getInsurerDto() == null) dto.setInsurerDto(new InsurerDto());
            break;
        case 10:
            if (dto.getReceptionistDto() == null) dto.setReceptionistDto(new ReceptionistDto());
            break;
    }
}
}
