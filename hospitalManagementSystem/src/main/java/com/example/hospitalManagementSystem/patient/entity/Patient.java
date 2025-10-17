package com.example.hospitalManagementSystem.patient.entity;

import com.example.hospitalManagementSystem.audit.Auditable;
import com.example.hospitalManagementSystem.authentication.entity.Address;
import com.example.hospitalManagementSystem.enums.Enums;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

//@Entity
//@Table(name = "patients")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Patient extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "patient_hospital_id", unique = true, nullable = false, length = 30)
    private String patientHospitalId;


    //  Relation with Address
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "email")
    private String email; // nullable allowed

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "age")
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Enums.Gender gender;


    @Column(name = "occupation")
    private String occupation;   //nullable allowed

    @Enumerated(EnumType.STRING)
    @Column(name = "blood_group")
    private Enums.BloodGroup bloodGroup;  //nullable allowed

    @Column(name = "contact_info", nullable = false)
    private String contactInfo;


    @Column(name="emergency_contact")
    private String emergencyContact;

    @Enumerated(EnumType.STRING)
    @Column(name="id_proof_type")
    private Enums.IdProofType idProofType;

    @Lob
    @Column(name="id_proof_file",columnDefinition = "BLOB")
    private byte[] idProofFile;


    @Enumerated(EnumType.STRING)
    @Column(name = "marital_status", nullable = false)
    private Enums.MaritalStatus maritalStatus;

    @Enumerated(EnumType.STRING)
    @Column(name ="visit_type" , nullable = false)
    private Enums.VisitType visitType;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note; // nullable
}
