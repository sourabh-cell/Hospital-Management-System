package com.example.hospitalManagementSystem.insurance.mapper;

import com.example.hospitalManagementSystem.authentication.entity.UserEntity;
import com.example.hospitalManagementSystem.insurance.dto.InsurerDto;
import com.example.hospitalManagementSystem.insurance.entity.Insurer;
import org.springframework.stereotype.Component;

//This class is for registration form
@Component
public class InsurerMapper {

    public Insurer toEntity(InsurerDto dto, UserEntity user) {
        Insurer insurer = new Insurer();

        insurer.setExperience(dto.getExperience().getLabel()); // Enum to string
        insurer.setQualifications(dto.getQualifications());
        insurer.setUserEntity(user);

        return insurer;

    }
}
