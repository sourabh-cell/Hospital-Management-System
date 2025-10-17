package com.example.hospitalManagementSystem.laboratory.repo;

import com.example.hospitalManagementSystem.laboratory.entity.Laboratorist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaboratoristRepo extends JpaRepository<Laboratorist,Long> {
}
