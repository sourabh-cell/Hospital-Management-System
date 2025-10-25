package com.example.hospitalManagementSystem.authentication.repositories;


import com.example.hospitalManagementSystem.authentication.entity.UserEntity;
import com.example.hospitalManagementSystem.authentication.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepo extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByUserEntity(UserEntity userEntity);


}
