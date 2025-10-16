package com.example.hospitalManagementSystem.ambulance.repo;

import com.example.hospitalManagementSystem.ambulance.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepo extends JpaRepository<Driver, Long> {
}
