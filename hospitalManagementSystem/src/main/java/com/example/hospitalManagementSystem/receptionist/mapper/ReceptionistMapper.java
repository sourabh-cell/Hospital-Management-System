package com.example.hospitalManagementSystem.receptionist.mapper;

import com.example.hospitalManagementSystem.authentication.entity.UserEntity;
import com.example.hospitalManagementSystem.receptionist.dto.ReceptionistDto;
import com.example.hospitalManagementSystem.receptionist.entity.Receptionist;
import org.springframework.stereotype.Component;

//This class is for registration form
@Component
public class ReceptionistMapper {

    public Receptionist toEntity(ReceptionistDto dto, UserEntity user) {
        Receptionist receptionist = new Receptionist();

        receptionist.setExperience(dto.getExperience().getLabel()); // Enum to string
        receptionist.setQualifications(dto.getQualifications());
        receptionist.setUserEntity(user);

        return receptionist;

    }
}
