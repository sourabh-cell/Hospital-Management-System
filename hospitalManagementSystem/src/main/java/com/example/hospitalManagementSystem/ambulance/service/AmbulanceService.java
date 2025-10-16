package com.example.hospitalManagementSystem.ambulance.service;

import com.example.hospitalManagementSystem.ambulance.dto.AmbulanceDTO;
import com.example.hospitalManagementSystem.ambulance.entity.Ambulance;

import java.util.List;

public interface AmbulanceService {

    void saveAmbulance(AmbulanceDTO ambulanceDTO);

    List<AmbulanceDTO> findAllAmbulance();  //only id and ambulance name

    Ambulance findAmbulanceById(Long id);

    List<AmbulanceDTO> findAllAmbulanceFullObject();
}
