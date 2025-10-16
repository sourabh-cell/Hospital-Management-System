package com.example.hospitalManagementSystem.RoomAndBedManager.dto;

import lombok.Data;

@Data
public class BedAllotedDTO {

    private Long bedAssignmentId;

    private String bedNumber;

    private String roomName;

    private String roomType;

    private String patientName;

}
