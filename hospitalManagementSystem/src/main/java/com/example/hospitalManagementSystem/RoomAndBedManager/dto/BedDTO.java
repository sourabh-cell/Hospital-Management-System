package com.example.hospitalManagementSystem.RoomAndBedManager.dto;

import com.example.hospitalManagementSystem.RoomAndBedManager.entity.Room;
import com.example.hospitalManagementSystem.enums.Enums;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class BedDTO {

    private Long bedId;


    @NotBlank(message = "Bed number is required")
    @Pattern(regexp = "^[0-9]+$", message = "Bed number must contain only digits")
    private String bedNumber;



    private Long roomId;



    private Enums.BedStatus status;


}
