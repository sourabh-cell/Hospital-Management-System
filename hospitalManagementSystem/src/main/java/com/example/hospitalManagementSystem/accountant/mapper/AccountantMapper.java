package com.example.hospitalManagementSystem.accountant.mapper;

import com.example.hospitalManagementSystem.accountant.dto.AccountantDto;
import com.example.hospitalManagementSystem.accountant.entity.Accountant;
import com.example.hospitalManagementSystem.authentication.entity.UserEntity;
import org.springframework.stereotype.Component;

//This class is for registration form
@Component
public class AccountantMapper {


    public Accountant toEntity(AccountantDto dto, UserEntity user) {
        Accountant accountant = new Accountant();

        accountant.setExperience(dto.getExperience().getLabel()); // Enum to string
        accountant.setQualifications(dto.getQualifications());
        accountant.setUserEntity(user);

        return accountant;

    }
}
