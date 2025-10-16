package com.example.hospitalManagementSystem.ambulance.dto;

import com.example.hospitalManagementSystem.ambulance.entity.Ambulance;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class DriverDTO {

    Long driverId;

    @NotBlank(message = "Driver name is required")
    @Pattern(regexp = "^[A-Za-z ]+$",
            message = "Driver name must contain only alphabets and spaces")
    private String driverName;


    @NotBlank
    private String licenseNumber;

    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "^[0-9]{10}$",
            message = "Contact number must be 10 digits")
    private String contactNumber;


    private Long ambulanceId;

    private Ambulance ambulance;
}
