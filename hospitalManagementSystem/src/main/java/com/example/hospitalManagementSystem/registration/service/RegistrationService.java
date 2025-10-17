package com.example.hospitalManagementSystem.registration.service;

import com.example.hospitalManagementSystem.registration.dto.RegistrationDto;
import jakarta.validation.Valid;

import java.io.IOException;

public interface RegistrationService {
    void registerUser(@Valid RegistrationDto dto) throws IOException;

}
