package com.example.hospitalManagementSystem.patient;

import com.example.hospitalManagementSystem.audit.Auditable;
import com.example.hospitalManagementSystem.authentication.entity.Address;
import com.example.hospitalManagementSystem.authentication.entity.UserInfo;
import com.example.hospitalManagementSystem.enums.Enums;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Table(name = "patients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Patient extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hospital_Patient_id",unique = true)
    private String hospitalPatientId;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;


}