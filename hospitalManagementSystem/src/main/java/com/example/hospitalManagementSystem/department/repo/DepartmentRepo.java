package com.example.hospitalManagementSystem.department.repo;

import com.example.hospitalManagementSystem.department.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepo extends JpaRepository<Department,Long> {

    @Query("SELECT d FROM Department d WHERE d.department_name = :department_name")
    Department findDepartmentByName(@Param("department_name") String department_name);
}
