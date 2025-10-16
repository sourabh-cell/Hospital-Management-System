package com.example.hospitalManagementSystem.RoomAndBedManager.service;

import com.example.hospitalManagementSystem.RoomAndBedManager.dto.BedDTO;
import com.example.hospitalManagementSystem.RoomAndBedManager.entity.Bed;
import com.example.hospitalManagementSystem.RoomAndBedManager.entity.Room;
import com.example.hospitalManagementSystem.RoomAndBedManager.repo.BedRepo;
import com.example.hospitalManagementSystem.RoomAndBedManager.repo.RoomRepo;
import com.example.hospitalManagementSystem.enums.Enums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BedServiceImpl implements BedService{

    @Autowired
    RoomRepo  roomRepo;

    @Autowired
    BedRepo bedRepo;

    // used to return all available rooms in hospital
    @Override
    public List<Room> getAllRoomsName() {
        List<Room> rooms = new ArrayList<>();
        rooms = roomRepo.findAll();
        return rooms;
    }

    @Override
    public String saveBed(BedDTO bedDTO)
    {
        Bed bed = new Bed();
        bed.setBedNumber(bedDTO.getBedNumber());
        bed.setRoom(roomRepo.findById(bedDTO.getRoomId()).orElseThrow(() -> new RuntimeException("Room not found")));
        bed.setStatus(Enums.BedStatus.VACCANT);
        System.out.println("bed Service");
        bedRepo.save(bed);
        return "Bed saved successfully";
    }
}
