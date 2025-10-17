package com.example.hospitalManagementSystem.accountant.dto;


import com.example.hospitalManagementSystem.enums.Enums;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AccountantDto {
    @NotNull(message = "Experience is required")
    private Enums.ExperienceLevel experience;


    @NotBlank(message = "Qualifications are required")
    @Size(max = 150, message = "Qualifications must be under 150 characters")
    private String qualifications;

    private Long userId;



}
