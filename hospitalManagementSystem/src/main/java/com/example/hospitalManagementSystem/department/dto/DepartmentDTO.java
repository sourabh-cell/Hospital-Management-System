package com.example.hospitalManagementSystem.department.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DepartmentDTO {

    private Long id;
    @NotNull
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Name must not contain numbers")
    private String department_name;

    private String description;

    @NotNull
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Department head must not contain numbers")
    private String department_head;

    private List<String> doctors;

}
