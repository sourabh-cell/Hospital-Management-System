package com.example.hospitalManagementSystem.laboratory.mapper;

import com.example.hospitalManagementSystem.authentication.entity.UserEntity;
import com.example.hospitalManagementSystem.laboratory.dto.LaboratoristDto;
import com.example.hospitalManagementSystem.laboratory.entity.Laboratorist;
import org.springframework.stereotype.Component;


//This class is for registration form
@Component
public class LaboratoristMapper {

    public Laboratorist toEntity(LaboratoristDto dto, UserEntity user) {
        Laboratorist laboratorist = new Laboratorist();

        laboratorist.setExperience(dto.getExperience().getLabel()); // Enum to string
        laboratorist.setQualifications(dto.getQualifications());
        laboratorist.setLaboratoryType(dto.getLaboratoryType());   // Enum to enum
        laboratorist.setUserEntity(user);

        return laboratorist;
    }

}
