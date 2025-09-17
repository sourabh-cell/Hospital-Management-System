package com.example.hospitalManagementSystem.department.service;

import com.example.hospitalManagementSystem.department.dto.DepartmentDTO;
import com.example.hospitalManagementSystem.department.entity.Department;
import com.example.hospitalManagementSystem.department.mapper.DepartmentMapper;
import com.example.hospitalManagementSystem.department.repo.DepartmentRepo;
import com.example.hospitalManagementSystem.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepo  departmentRepo;

    @Autowired
    DepartmentMapper departmentMapper;


    @Override
    public DepartmentDTO findById(Long department_id)
    {
       Department department = departmentRepo.findById(department_id)
               .orElseThrow(()-> new ResourceNotFoundException("Department Not Found With Id :- "+department_id));

         return departmentMapper.toDto(department);

    }

    @Override
    public Department findByName(String dept_name) {
        return departmentRepo.findDepartmentByName(dept_name);
    }

    @Override
    public List<DepartmentDTO> findAll() {
        return departmentRepo.findAll()
                //converting department to dto for view
                .stream().map(departmentMapper::toDto)
                .toList();
    }

    @Override
    public Department save(Department department) {
        return departmentRepo.save(department);
    }

    @Override
    @Transactional
    public void delete(Long departmentId) {
        departmentRepo.deleteById(departmentId);
    }

    @Override
    public String udateDepartment(Long department_id,Department department) {
        department.setId(department_id);
        departmentRepo.save(department);
        return "Department Updated Successfully";
    }
}
