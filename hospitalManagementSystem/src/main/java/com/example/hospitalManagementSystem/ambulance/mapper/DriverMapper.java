package com.example.hospitalManagementSystem.ambulance.mapper;

import com.example.hospitalManagementSystem.ambulance.dto.DriverDTO;
import com.example.hospitalManagementSystem.ambulance.entity.Ambulance;
import com.example.hospitalManagementSystem.ambulance.entity.Driver;
import com.example.hospitalManagementSystem.ambulance.repo.AmbulanceRepo;
import com.example.hospitalManagementSystem.ambulance.repo.DriverRepo;
import com.example.hospitalManagementSystem.ambulance.service.AmbulanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DriverMapper {

    @Autowired
    private AmbulanceService ambulanceService;

    public  DriverDTO mapDriverToDriverDTO(Driver driver)
    {
        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setDriverName(driver.getDriverName());
        driverDTO.setContactNumber(driver.getContactNumber());
        driverDTO.setLicenseNumber(driver.getLicenseNumber());
        driverDTO.setAmbulanceId(driver.getAmbulance().getId());
        driverDTO.setAmbulance(driver.getAmbulance());
        return driverDTO;
    }

    public Driver mapDriverDTOtoDriver(DriverDTO driverDTO)
    {
        Driver driver = new Driver();
        driver.setDriverName(driverDTO.getDriverName());
        driver.setContactNumber(driverDTO.getContactNumber());
        driver.setLicenseNumber(driverDTO.getLicenseNumber());

        Ambulance ambulance = new Ambulance();
        ambulance = ambulanceService.findAmbulanceById(driverDTO.getAmbulanceId());
        driver.setAmbulance(ambulance);
        return driver;
    }
}
