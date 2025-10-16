package com.example.hospitalManagementSystem.RoomAndBedManager.service;

import com.example.hospitalManagementSystem.RoomAndBedManager.dto.BedAllotedDTO;
import com.example.hospitalManagementSystem.RoomAndBedManager.dto.BedAssignmentDTO;
import com.example.hospitalManagementSystem.RoomAndBedManager.dto.BedDTO;
import com.example.hospitalManagementSystem.RoomAndBedManager.dto.RoomDTO;
import com.example.hospitalManagementSystem.RoomAndBedManager.entity.Bed;
import com.example.hospitalManagementSystem.RoomAndBedManager.entity.BedAssignment;
import com.example.hospitalManagementSystem.RoomAndBedManager.entity.Room;
import com.example.hospitalManagementSystem.RoomAndBedManager.repo.BedAssignmentRepo;
import com.example.hospitalManagementSystem.RoomAndBedManager.repo.BedRepo;
import com.example.hospitalManagementSystem.RoomAndBedManager.repo.RoomRepo;
import com.example.hospitalManagementSystem.authentication.entity.CustomUserDetails;
import com.example.hospitalManagementSystem.authentication.entity.UserEntity;
import com.example.hospitalManagementSystem.enums.Enums;
import com.example.hospitalManagementSystem.patient.Patient;
import com.example.hospitalManagementSystem.patient.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BedAssignmentServiceImpl implements BedAssignmentService {

    @Autowired
    BedAssignmentRepo bedAssignmentRepo;

    @Autowired
    RoomRepo roomRepo;

    @Autowired
    PatientRepo patientRepo;

    @Autowired
    BedRepo bedRepo;

    // Declaration of class for getting current user object
    CustomUserDetails customUserDetails;


    @Override
    public BedAssignment findBedAssignmentById(long id) {
        return null;
    }

    @Override
    @Transactional
    public BedAssignment saveBedAssignment(BedAssignmentDTO bedAssignmentDTO) {

        // check if patient already assigned
        long activeAssignments = bedAssignmentRepo.countActiveAssignmentsForPatient(bedAssignmentDTO.getPatientId());

        if (activeAssignments > 0) {
            throw new IllegalStateException("Patient is already assigned to a bed.");
        }


        // getting object of bed to Alloted
        Bed bed = bedRepo.findById(bedAssignmentDTO.getBedId())
                .orElseThrow(() -> new RuntimeException("Bed not found"));



        // Update status
        bed.setStatus(Enums.BedStatus.OCCUPIED);
        bedRepo.save(bed);


        BedAssignment bedAssignment =  new BedAssignment();

        bedAssignment.setBed(bed);

        //Current user
        UserEntity currentUser = ((CustomUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        bedAssignment.setAssignedBy(currentUser);

        //setting patient
        bedAssignment.setPatient(patientRepo.findById(bedAssignmentDTO.getPatientId()).orElseThrow(() -> new RuntimeException("Bed not found")));

        bedAssignment.setAssignedAt(LocalDateTime.now());



       return bedAssignmentRepo.save(bedAssignment);
    }

    // method to show data view vaccant beds in Rooms
    @Override
    public List<RoomDTO> findAllTotalVaccantBedsByRoom() {

       return roomRepo.findAll().stream().
               map(r->
               {
                   RoomDTO roomDTO = new RoomDTO();
                   roomDTO.setRoomId(r.getId());
                   roomDTO.setRoomNumber(r.getRoomNumber());
                   roomDTO.setRoomName(r.getRoomName());
                   roomDTO.setRoomType(r.getRoomType().getRoomTypeName().toUpperCase());
                   roomDTO.setTotalBeds(r.getBeds().stream().count());
                   roomDTO.setVacantBeds(r.getBeds().stream().filter(bed ->
                                         bed.getStatus().equals(Enums.BedStatus.VACCANT)).count());
                   return roomDTO;
               }).toList();
    }

    // give all generated patiend id of hospital
    @Override
    public Map<Long,String> findAllPatientId() {

        return patientRepo.findAll().stream().
               collect(Collectors.toMap(
                       Patient::getId,
                       Patient::getHospitalPatientId
               ));
    }


    // method to show data during assigning of beds
    public RoomDTO showRoomDTOById(long roomId) {
        Room room = roomRepo.findById(roomId).orElse(null);
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomId(room.getId());
        roomDTO.setRoomName(room.getRoomName());
        roomDTO.setRoomType(room.getRoomType().getRoomTypeName().toUpperCase());
        return roomDTO;
    }


    //showing information of alloted bed
    @Override
    public List<BedAllotedDTO> showAllotedBed()
    {
       return  bedAssignmentRepo.findCurrentlyAssignedBeds().stream()
               .map(b -> {
                   BedAllotedDTO bedAllotedDTO = new BedAllotedDTO();
                   bedAllotedDTO.setBedAssignmentId(b.getId());
                   bedAllotedDTO.setBedNumber(b.getBed().getBedNumber());
                   bedAllotedDTO.setPatientName(b.getPatient().getFirstName()+" "+b.getPatient().getLastName());
                   bedAllotedDTO.setRoomName(b.getBed().getRoom().getRoomName());
                   bedAllotedDTO.setRoomType(b.getBed().getRoom().getRoomType().getRoomTypeName().toUpperCase());
                   return bedAllotedDTO;
                       }
               ).toList();
    }

    @Override
    @Transactional
    public String releaseBed(Long bedAssignmentId) {

        //setting release date in bed assignment
        BedAssignment  bedAssignment = bedAssignmentRepo.findById(bedAssignmentId).orElseThrow(() -> new RuntimeException("Bed not found"));
        bedAssignment.setReleasedAt(LocalDateTime.now());
        bedAssignmentRepo.save(bedAssignment);


        //changing Status of Bed
       Bed bed= bedAssignment.getBed();
       bed.setStatus(Enums.BedStatus.VACCANT);
       bedRepo.save(bed);


        return "Bed Released";
    }

    //checking if room have any vaccant bed
    @Override
    public boolean checkingVacantBedInRoom(Long roomId)
    {

        return roomRepo.findById(roomId)
                .map(room -> room.getBeds().stream()
                        .anyMatch(bed -> bed.getStatus() == Enums.BedStatus.VACCANT)) // true if at least 1 bed is vacant
                .orElse(false); // if room not found, return false

    }

    //method to show all vacant beds number in room
    public Map<Long, String> showAllAvailableBedNumbers(Long roomId) {
        return roomRepo.findById(roomId)
                .map(room -> room.getBeds().stream()
                        .filter(b -> b.getStatus() == Enums.BedStatus.VACCANT) // only vacant
                        .collect(Collectors.toMap(
                                Bed::getId,       // key = bed ID
                                Bed::getBedNumber // value = bed number
                        ))
                )
                .orElseGet(Collections::emptyMap);
    }






}
