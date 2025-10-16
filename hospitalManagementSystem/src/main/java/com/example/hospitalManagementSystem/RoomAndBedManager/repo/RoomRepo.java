package com.example.hospitalManagementSystem.RoomAndBedManager.repo;

import com.example.hospitalManagementSystem.RoomAndBedManager.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepo extends JpaRepository<Room , Long>{
}
