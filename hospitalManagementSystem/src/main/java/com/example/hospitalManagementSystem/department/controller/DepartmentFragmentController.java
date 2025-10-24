package com.example.hospitalManagementSystem.department.controller;

import com.example.hospitalManagementSystem.department.dto.DepartmentDTO;
import com.example.hospitalManagementSystem.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DepartmentFragmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/fragment/department/registration")
    @PreAuthorize("hasAuthority('DEPARTMENT_ADD')")
    public String fragmentDepartmentForm(Model model)
    {
        model.addAttribute("department",new DepartmentDTO());
        return "department/department-form :: departmentFormFragment";
    }

    @GetMapping("fragment/department/list")
    @PreAuthorize("hasAuthority('DEPARTMENT_VIEW')")
    public String listDepartments(Model  model)
    {
        model.addAttribute("departments",departmentService.findAll());
        return "department/department-list :: departmentListFragment";
    }

    @GetMapping("fragment/department/update/{id}")
    @PreAuthorize("hasAuthority('DEPARTMENT_UPDATE')")
    public String fragmentDepartmentUpdate(@PathVariable Long id, Model  model)
    {
        DepartmentDTO department =  departmentService.findById(id);
        model.addAttribute("department",department);
        System.out.println("Department Update Fragment controller");
        return "department/department-update :: departmentUpdateFragment";
    }


}
