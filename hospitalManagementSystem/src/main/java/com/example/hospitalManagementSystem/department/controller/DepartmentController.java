package com.example.hospitalManagementSystem.department.controller;

import com.example.hospitalManagementSystem.authentication.entity.UserEntity;
import com.example.hospitalManagementSystem.department.dto.DepartmentDTO;
import com.example.hospitalManagementSystem.department.entity.Department;
import com.example.hospitalManagementSystem.department.mapper.DepartmentMapper;
import com.example.hospitalManagementSystem.department.repo.DepartmentRepo;
import com.example.hospitalManagementSystem.department.service.DepartmentService;
import com.example.hospitalManagementSystem.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.hospitalManagementSystem.exception.ResourceNotFoundException;

import java.util.List;

@Controller
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @Autowired
    DepartmentMapper departmentMapper;

    @GetMapping("/department/registration")
    @PreAuthorize("hasAuthority('DEPARTMENT_ADD')")
    public String departmentForm()
    {

        return "index";
    }


    @PostMapping("/department/add")
    @PreAuthorize("hasAuthority('DEPARTMENT_ADD')")
    public ResponseEntity<DepartmentDTO> addDepartment(@ModelAttribute("department") DepartmentDTO dto)
    {
        // department dto converted to Department using toEntity method
        Department department = departmentMapper.toEntity(dto);
        // saving department
        Department saved = departmentService.save(department);
        // converting the department which is saved to dto using toDto method and sending response
        return ResponseEntity.ok(departmentMapper.toDto(saved));

    }

    @GetMapping("/department/list")
    @PreAuthorize("hasAuthority('DEPARTMENT_VIEW')")
    public String listDepartments(Model  model)
    {
        model.addAttribute("departments",departmentService.findAll());
        return "index";
    }

    @GetMapping("/department/delete/{id}")
    @PreAuthorize("hasAuthority('DEPARTMENT_DELETE')")
    public String  deleteDepartment(@PathVariable Long id)
    {
         departmentService.delete(id);
         return "redirect:/department/list";
    }

    @GetMapping("/department/update/{id}")
    @PreAuthorize("hasAuthority('DEPARTMENT_UPDATE')")
    public String showUpdate()
    {
        return "index";
    }

    @PostMapping("/department/update/{id}")
    @PreAuthorize("hasAuthority('DEPARTMENT_UPDATE')")
    public String updateDepartment(@PathVariable Long id, @ModelAttribute("department") Department department, Model model) {
        departmentService.udateDepartment(id, department);

        // Load all departments again for the list fragment
        model.addAttribute("departments", departmentService.findAll());
        return "department/department-list :: departmentListFragment";
    }


}
