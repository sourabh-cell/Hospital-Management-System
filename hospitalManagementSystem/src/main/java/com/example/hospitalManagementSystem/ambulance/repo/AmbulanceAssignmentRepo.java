package com.example.hospitalManagementSystem.ambulance.repo;

import com.example.hospitalManagementSystem.ambulance.entity.AmbulanceAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmbulanceAssignmentRepo extends JpaRepository<AmbulanceAssignment, Long> {
}
