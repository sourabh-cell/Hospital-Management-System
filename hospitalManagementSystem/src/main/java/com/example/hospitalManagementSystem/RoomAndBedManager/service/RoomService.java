package com.example.hospitalManagementSystem.RoomAndBedManager.service;

import com.example.hospitalManagementSystem.RoomAndBedManager.dto.CreateRoomDTO;
import com.example.hospitalManagementSystem.RoomAndBedManager.entity.Room;
import com.example.hospitalManagementSystem.RoomAndBedManager.entity.RoomType;

import java.util.List;


public interface RoomService {

    void createRoom(CreateRoomDTO dto);

    Room findRoomById(Long id);

    List<String> findAllAvailaibleRoomTypes();


}
