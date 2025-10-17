package com.example.hospitalManagementSystem.insurance.repo;

import com.example.hospitalManagementSystem.insurance.entity.Insurer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsurerRepo extends JpaRepository<Insurer, Long> {
}
