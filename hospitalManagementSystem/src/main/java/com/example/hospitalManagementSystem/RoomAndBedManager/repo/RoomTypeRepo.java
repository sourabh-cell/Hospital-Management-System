package com.example.hospitalManagementSystem.RoomAndBedManager.repo;

import com.example.hospitalManagementSystem.RoomAndBedManager.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomTypeRepo extends JpaRepository<RoomType,Long> {

    RoomType findRoomTypeByroomTypeName(String roomTypeName);
}
