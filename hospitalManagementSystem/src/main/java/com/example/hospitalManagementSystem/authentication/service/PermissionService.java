package com.example.hospitalManagementSystem.authentication.service;

import com.example.hospitalManagementSystem.authentication.entity.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> getUserPermissionsByUserId(Long UserId);
}
