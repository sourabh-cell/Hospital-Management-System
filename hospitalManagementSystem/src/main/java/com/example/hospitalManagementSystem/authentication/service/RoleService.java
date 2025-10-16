package com.example.hospitalManagementSystem.authentication.service;

import com.example.hospitalManagementSystem.authentication.entity.Role;

import java.util.List;


public interface RoleService {

    List<Role> getAllRoles();

    Role getRoleById(Long id);
}
