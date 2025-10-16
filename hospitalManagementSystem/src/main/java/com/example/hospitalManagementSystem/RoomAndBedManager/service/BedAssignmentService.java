package com.example.hospitalManagementSystem.RoomAndBedManager.service;

import com.example.hospitalManagementSystem.RoomAndBedManager.dto.BedAllotedDTO;
import com.example.hospitalManagementSystem.RoomAndBedManager.dto.BedAssignmentDTO;
import com.example.hospitalManagementSystem.RoomAndBedManager.dto.RoomDTO;
import com.example.hospitalManagementSystem.RoomAndBedManager.entity.BedAssignment;
import com.example.hospitalManagementSystem.RoomAndBedManager.entity.Room;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface BedAssignmentService
{
    BedAssignment findBedAssignmentById(long id);

    BedAssignment saveBedAssignment(BedAssignmentDTO bedAssignmentDTO);

    List<RoomDTO>  findAllTotalVaccantBedsByRoom();

    Map<Long,String> findAllPatientId();

    Map<Long, String> showAllAvailableBedNumbers(Long roomId);

     RoomDTO showRoomDTOById(long roomId);

     List<BedAllotedDTO>  showAllotedBed();

     String releaseBed(Long bedId);

     boolean checkingVacantBedInRoom(Long  roomId);




}
