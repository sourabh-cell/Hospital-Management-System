package com.example.hospitalManagementSystem.RoomAndBedManager.service;

import com.example.hospitalManagementSystem.RoomAndBedManager.dto.CreateRoomDTO;
import com.example.hospitalManagementSystem.RoomAndBedManager.entity.Room;
import com.example.hospitalManagementSystem.RoomAndBedManager.entity.RoomType;
import com.example.hospitalManagementSystem.RoomAndBedManager.repo.RoomRepo;
import com.example.hospitalManagementSystem.RoomAndBedManager.repo.RoomTypeRepo;
import com.example.hospitalManagementSystem.enums.Enums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    RoomRepo roomRepo;
    @Autowired
    RoomTypeRepo roomTypeRepo;


    @Override
    public void createRoom(CreateRoomDTO dto)
    {
       // checking if room type is available or not if not then filling data of another room type
       RoomType roomType = roomTypeRepo.findRoomTypeByroomTypeName(dto.getRoomTypeName());
       if(roomType==null)
       {
           roomType=new RoomType();
           roomType.setRoomTypeName(dto.getRoomTypeName());
           roomType.setDescription(dto.getDescription());
           roomType.setPricePerDay(dto.getPricePerDay());
           roomTypeRepo.save(roomType);

       }

        Room room = new Room();
        room.setRoomNumber(dto.getRoomNo());
        room.setFloorNumber(dto.getFloor());
        room.setStatus(Enums.RoomStatus.valueOf(dto.getStatus().toUpperCase()));
        room.setRoomName(dto.getRoomName().toUpperCase());
        room.setRoomType(roomType);

        roomRepo.save(room);



    }

    @Override
    public Room findRoomById(Long id) {
        return roomRepo.findById(id).get();
    }

    @Override
    public List<String> findAllAvailaibleRoomTypes() {
        List<String> roomType= roomTypeRepo.findAll().stream()
                .map(rt->rt.getRoomTypeName().toUpperCase()).collect(Collectors.toList());

        return roomType;
    }


}
