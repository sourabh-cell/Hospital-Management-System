package com.example.hospitalManagementSystem.department.service;

import com.example.hospitalManagementSystem.department.dto.DepartmentDTO;
import com.example.hospitalManagementSystem.department.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    DepartmentDTO findById(Long id);

    Department findByName(String name);

    List<DepartmentDTO> findAll();

    Department save(Department department);

    void delete(Long departmentId);

    String udateDepartment(Long department_id,Department department);
}
