package com.example.hospitalManagementSystem.doctor.service;

import com.example.hospitalManagementSystem.patient.Patient;
import com.example.hospitalManagementSystem.patient.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepo patientRepo;

    List<Patient> findAllPatient()
    {
        return patientRepo.findAll();
    }
}
