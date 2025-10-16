package com.example.hospitalManagementSystem.RoomAndBedManager.dto;

import com.example.hospitalManagementSystem.RoomAndBedManager.entity.Bed;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

// this dto is only for showing room data
@Data
public class RoomDTO {

    private Long roomId;


    private String roomName;


    private String roomNumber;

    private String roomType;

    private Long totalBeds;

    private Long vacantBeds;

    private List<Bed>  beds;

}
