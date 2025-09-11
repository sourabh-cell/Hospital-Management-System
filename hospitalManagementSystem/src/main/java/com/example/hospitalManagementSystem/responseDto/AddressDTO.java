package com.example.hospitalManagementSystem.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for transferring address details
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressDTO {
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private String pincode;
}