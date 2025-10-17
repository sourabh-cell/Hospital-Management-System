package com.example.hospitalManagementSystem.doctor.mapper;

import com.example.hospitalManagementSystem.authentication.entity.UserEntity;
import com.example.hospitalManagementSystem.department.entity.Department;
import com.example.hospitalManagementSystem.department.repo.DepartmentRepo;
import com.example.hospitalManagementSystem.doctor.dto.DoctorDto;
import com.example.hospitalManagementSystem.doctor.entity.Doctor;
import com.example.hospitalManagementSystem.enums.Enums;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


//This class is for registration form
@Component
@RequiredArgsConstructor
public class DoctorMapper {

    private final DepartmentRepo departmentRepo;

    public Doctor toEntity(DoctorDto dto, UserEntity user) {
        Doctor doctor = new Doctor();

        // Set department from ID
        Department department = departmentRepo.findById(dto.getDepartmentId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid department ID"));
        doctor.setDepartment(department);

        // Map remaining fields
        doctor.setSpecialization(dto.getSpecialization());
        doctor.setExperience(dto.getExperience().getLabel()); // Enum to string
        doctor.setQualifications(dto.getQualifications());
        doctor.setLicenseNumber(dto.getLicenseNumber());

        // Default availability status
        doctor.setAvailabilityStatus(Enums.AvailabilityStatus.ACTIVE);

        // Link user
        doctor.setUserEntity(user);

        return doctor;
    }
}
