package com.example.hospitalManagementSystem.laboratory.entity;

import com.example.hospitalManagementSystem.audit.Auditable;
import com.example.hospitalManagementSystem.authentication.entity.UserEntity;
import com.example.hospitalManagementSystem.enums.Enums;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "laboratorist")
public class Laboratorist extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "experience", nullable = false)
    private String experience;

    @Column(name = "qualifications", nullable = false)
    private String qualifications;

    @Enumerated(EnumType.STRING)
    private Enums.LaboratoryType laboratoryType;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

}
