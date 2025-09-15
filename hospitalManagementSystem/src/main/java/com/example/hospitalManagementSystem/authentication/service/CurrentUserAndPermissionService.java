package com.example.hospitalManagementSystem.authentication.service;

import com.example.hospitalManagementSystem.authentication.entity.CustomUserDetails;
import com.example.hospitalManagementSystem.authentication.entity.Permission;

import java.util.List;

public interface CurrentUserAndPermissionService {
    CustomUserDetails getCurrentUser();
    List<Permission> getPermissions();
}
