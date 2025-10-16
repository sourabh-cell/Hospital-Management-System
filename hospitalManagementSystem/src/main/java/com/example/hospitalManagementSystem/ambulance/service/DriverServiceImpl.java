package com.example.hospitalManagementSystem.ambulance.service;

import com.example.hospitalManagementSystem.ambulance.dto.DriverDTO;
import com.example.hospitalManagementSystem.ambulance.entity.Driver;
import com.example.hospitalManagementSystem.ambulance.mapper.DriverMapper;
import com.example.hospitalManagementSystem.ambulance.repo.DriverRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverRepo driverRepo;

    @Autowired
    DriverMapper driverMapper;

    @Override
    public void saveDriver(DriverDTO driverDTO)
    {
      Driver driver = driverMapper.mapDriverDTOtoDriver(driverDTO);
      driverRepo.save(driver);

    }

    @Override
    public Driver findDriverById(Long id) {
       return driverRepo.findById(id).
               orElseThrow(()->new RuntimeException("Driver not found"));
    }

    @Override
    public List<DriverDTO> findAllDriver() {
        return driverRepo.findAll().stream().
                map(d->
                {
                    DriverDTO driverDTO = new DriverDTO();
                    driverDTO.setDriverId(d.getId());
                    driverDTO.setDriverName(d.getDriverName());
                    return driverDTO;
                }).collect(Collectors.toList());
    }

    @Override
    public List<DriverDTO> findAllDriverFullObject() {
        return driverRepo.findAll().stream().
                map(d->{
                    DriverDTO driverDTO = new DriverDTO();
                    driverDTO = driverMapper.mapDriverToDriverDTO(d);
                    return driverDTO;
                }).collect(Collectors.toList());
    }


}
