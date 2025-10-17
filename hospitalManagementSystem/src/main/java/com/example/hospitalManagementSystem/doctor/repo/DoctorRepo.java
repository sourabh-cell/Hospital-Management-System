package com.example.hospitalManagementSystem.doctor.repo;

import com.example.hospitalManagementSystem.doctor.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepo extends JpaRepository<Doctor, Long> {
}
