package com.example.hospitalManagementSystem.ambulance.service;

import com.example.hospitalManagementSystem.ambulance.dto.AmbulanceAssignmentDTO;
import com.example.hospitalManagementSystem.ambulance.entity.AmbulanceAssignment;

import java.util.List;

public interface AmbulanceAssignmentService {

    void saveAmbulanceAssignment(AmbulanceAssignmentDTO ambulanceAssignmentDTO);

    List<AmbulanceAssignmentDTO> findAllCompletedAmbulanceAssignments();

    void updateAssignmentStatus(Long id, String status);

    List<AmbulanceAssignmentDTO> findAllInProgressAmbulanceAssignments();
}
