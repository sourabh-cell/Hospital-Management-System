package com.example.hospitalManagementSystem.ambulance.mapper;

import com.example.hospitalManagementSystem.ambulance.dto.AmbulanceDTO;
import com.example.hospitalManagementSystem.ambulance.entity.Ambulance;
import com.example.hospitalManagementSystem.enums.AmbulanceEnums;
import org.springframework.stereotype.Component;

@Component
public class AmbulanceMapper {

    public AmbulanceDTO ambulanceToCreateAmbulanceDTO(Ambulance ambulance)
    {
        AmbulanceDTO dto = new AmbulanceDTO();
        dto.setAmbulanceId(ambulance.getId());
        dto.setVehicleNumber(ambulance.getVehicleNumber());
        dto.setAmbulanceType(String.valueOf(ambulance.getAmbulanceType()));
        dto.setAmbulanceStatus(String.valueOf(ambulance.getAmbulanceStatus()));
        if (ambulance.getLastMaintenanceDate() != null) {
            dto.setLastMaintenanceDate(ambulance.getLastMaintenanceDate());
        }
        return dto;
    }

    public Ambulance createDtoToAmbulance(AmbulanceDTO dto)
    {
        Ambulance ambulance = new Ambulance();
        ambulance.setVehicleNumber(dto.getVehicleNumber());
        ambulance.setAmbulanceType(AmbulanceEnums.AmbulanceType.valueOf(dto.getAmbulanceType()));
        ambulance.setAmbulanceStatus(AmbulanceEnums.AmbulanceStatus.valueOf(dto.getAmbulanceStatus()));
        if (dto.getLastMaintenanceDate() != null)
        {
            ambulance.setLastMaintenanceDate(dto.getLastMaintenanceDate());
        }
        return ambulance;
    }
}
