package com.example.hospitalManagementSystem.ambulance.service;

import com.example.hospitalManagementSystem.ambulance.dto.AmbulanceDTO;
import com.example.hospitalManagementSystem.ambulance.entity.Ambulance;
import com.example.hospitalManagementSystem.ambulance.mapper.AmbulanceMapper;
import com.example.hospitalManagementSystem.ambulance.repo.AmbulanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AmbulanceServiceImpl implements AmbulanceService {

    @Autowired
    AmbulanceRepo  ambulanceRepo;
    @Autowired
    AmbulanceMapper   ambulanceMapper;

    @Override
    public void saveAmbulance(AmbulanceDTO ambulanceDTO)
    {
        Ambulance ambulance = ambulanceMapper.createDtoToAmbulance(ambulanceDTO);
        ambulanceRepo.save(ambulance);
    }

    // sending only id and name of ambulance
    @Override
    public List<AmbulanceDTO> findAllAmbulance() {
        return ambulanceRepo.findAll().stream()
                .map(a->
                {
                    AmbulanceDTO ambulanceDTO = new AmbulanceDTO();
                    ambulanceDTO.setAmbulanceId(a.getId());
                    ambulanceDTO.setVehicleNumber(a.getVehicleNumber());
                    return ambulanceDTO;
                }).toList();
    }

    @Override
    public Ambulance findAmbulanceById(Long id) {
        return ambulanceRepo.findById(id).orElseThrow(()->new RuntimeException("ambulance not found"));
    }

    //sending full data of ambulance
    @Override
    public List<AmbulanceDTO> findAllAmbulanceFullObject() {
        return ambulanceRepo.findAll().stream().
                map(a->
                {
                    AmbulanceDTO ambulanceDTO = new AmbulanceDTO();
                    ambulanceDTO = ambulanceMapper.ambulanceToCreateAmbulanceDTO(a);
                    return ambulanceDTO;
                }).collect(Collectors.toList());
    }
}
