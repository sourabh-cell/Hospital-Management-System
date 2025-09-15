package com.example.hospitalManagementSystem.authentication.service;

import com.example.hospitalManagementSystem.authentication.entity.Permission;
import com.example.hospitalManagementSystem.authentication.repositories.PermissionRepo;
import com.example.hospitalManagementSystem.authentication.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
   private  UserRepo userRepo;

    @Autowired
    private PermissionRepo permissionRepo;


    @Override
    public List<Permission> getUserPermissionsByUserId(Long userId) {
        // Super Admin and Admin get ALL permissions
        boolean isSuperAdmin = userRepo.hasRole(userId, "ROLE_SUPER_ADMIN");
        boolean isAdmin = userRepo.hasRole(userId, "ROLE_ADMIN");

        if (isSuperAdmin || isAdmin) {
            return permissionRepo.findAll();
        }

        // Normal users → fetch role-based permissions
        return permissionRepo.findPermissionsByUserId(userId);
    }
}
