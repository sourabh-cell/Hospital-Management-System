package com.example.hospitalManagementSystem.receptionist.repo;

import com.example.hospitalManagementSystem.receptionist.entity.Receptionist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceptionistRepo extends JpaRepository<Receptionist,Long> {
}
