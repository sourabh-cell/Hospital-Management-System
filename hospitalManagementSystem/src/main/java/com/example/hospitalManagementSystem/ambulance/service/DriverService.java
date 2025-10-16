package com.example.hospitalManagementSystem.ambulance.service;

import com.example.hospitalManagementSystem.ambulance.dto.DriverDTO;
import com.example.hospitalManagementSystem.ambulance.entity.Driver;

import java.util.List;

public interface DriverService {

    void saveDriver(DriverDTO driverDTO);

    Driver findDriverById(Long id);

    List<DriverDTO> findAllDriver();  //send only id and name

    List<DriverDTO> findAllDriverFullObject(); // send full data
}
