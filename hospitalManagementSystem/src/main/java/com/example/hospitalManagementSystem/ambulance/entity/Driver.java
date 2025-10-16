package com.example.hospitalManagementSystem.ambulance.entity;

import com.example.hospitalManagementSystem.audit.Auditable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "driver")
public class Driver extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String driverName;

    @Column(name = "license_number", nullable = false, unique = true)
    private String licenseNumber;

    @Column(name = "contact_number", nullable = false)
    private String contactNumber;

    @ManyToOne
    @JoinColumn(name = "ambulance_id")
    private Ambulance ambulance;

}
