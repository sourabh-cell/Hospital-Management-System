package com.example.hospitalManagementSystem.hr.mapper;

import com.example.hospitalManagementSystem.authentication.entity.UserEntity;
import com.example.hospitalManagementSystem.hr.dto.HumanResourceDto;
import com.example.hospitalManagementSystem.hr.enity.HumanResource;
import org.springframework.stereotype.Component;

//This class is for registration form
@Component
public class HumanResourceMapper {
    public HumanResource toEntity(HumanResourceDto dto, UserEntity user) {
        HumanResource hr = new HumanResource();

        hr.setExperience(dto.getExperience().getLabel()); // Enum to string
        hr.setQualifications(dto.getQualifications());
        hr.setUserEntity(user);

        return hr;
    }
}
