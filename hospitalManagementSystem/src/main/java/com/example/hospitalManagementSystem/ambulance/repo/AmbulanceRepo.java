package com.example.hospitalManagementSystem.ambulance.repo;

import com.example.hospitalManagementSystem.ambulance.entity.Ambulance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmbulanceRepo extends JpaRepository<Ambulance, Long> {
}
