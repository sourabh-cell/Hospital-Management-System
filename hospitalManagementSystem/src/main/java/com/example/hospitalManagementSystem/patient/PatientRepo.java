package com.example.hospitalManagementSystem.patient;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepo extends JpaRepository<Patient,Long>
{


}
