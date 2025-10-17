package com.example.hospitalManagementSystem.pharmacy.repo;

import com.example.hospitalManagementSystem.pharmacy.entity.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacistRepo extends JpaRepository<Pharmacist, Long> {
}
