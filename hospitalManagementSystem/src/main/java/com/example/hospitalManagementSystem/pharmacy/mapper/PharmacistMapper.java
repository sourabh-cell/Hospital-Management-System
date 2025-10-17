package com.example.hospitalManagementSystem.pharmacy.mapper;

import com.example.hospitalManagementSystem.authentication.entity.UserEntity;
import com.example.hospitalManagementSystem.pharmacy.dto.PharmacistDto;
import com.example.hospitalManagementSystem.pharmacy.entity.Pharmacist;
import org.springframework.stereotype.Component;


//This class is for registration form
@Component
public class PharmacistMapper {

    public Pharmacist toEntity(PharmacistDto dto, UserEntity user) {
        Pharmacist pharmacist = new Pharmacist();

        pharmacist.setExperience(dto.getExperience().getLabel()); // Enum to string
        pharmacist.setQualifications(dto.getQualifications());
        pharmacist.setUserEntity(user);

        return pharmacist;
    }

}
