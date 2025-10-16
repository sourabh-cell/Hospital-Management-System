package com.example.hospitalManagementSystem.RoomAndBedManager.repo;

import com.example.hospitalManagementSystem.RoomAndBedManager.entity.Bed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BedRepo extends JpaRepository<Bed, Long> {



}
