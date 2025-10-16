package com.example.hospitalManagementSystem.RoomAndBedManager.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BedAssignmentDTO {

    private Long id;

    // IDs instead of full entities
    private Long bedId;

    private Long patientHospitalId;

    private Long patientId;

    private Long assignedById;

    private LocalDateTime assignedAt;

    private LocalDateTime releasedAt;



}
