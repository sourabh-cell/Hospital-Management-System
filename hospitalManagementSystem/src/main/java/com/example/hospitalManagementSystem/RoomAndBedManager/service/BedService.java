package com.example.hospitalManagementSystem.RoomAndBedManager.service;

import com.example.hospitalManagementSystem.RoomAndBedManager.dto.BedDTO;
import com.example.hospitalManagementSystem.RoomAndBedManager.entity.Room;

import java.util.List;

public interface BedService {


    List<Room> getAllRoomsName();


    String saveBed(BedDTO bedDTO);

}
