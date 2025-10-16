package com.example.hospitalManagementSystem.authentication.service;

import com.example.hospitalManagementSystem.authentication.entity.Role;
import com.example.hospitalManagementSystem.authentication.repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepo roleRepo;

    @Override
    public List<Role> getAllRoles() {
        return roleRepo.findAll();
    }

    @Override
    public Role getRoleById(Long id) {
      return  roleRepo.findById(id).orElseThrow(()-> new RuntimeException("Role Not Found with Id:- "+id));
    }
}

