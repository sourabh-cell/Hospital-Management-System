package com.example.hospitalManagementSystem.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO for new user registration
 * Includes user + user info + address in one request.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRegistrationDTO {
    private String username;
    private String email;
    private String password; // Needed only at registration
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String gender;
    private LocalDate dob;
    private String bloodGroup;

    // Address
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private String pincode;

    // Role assignment
    private String roleName; // Example: "ADMIN" or "DOCTOR"


}