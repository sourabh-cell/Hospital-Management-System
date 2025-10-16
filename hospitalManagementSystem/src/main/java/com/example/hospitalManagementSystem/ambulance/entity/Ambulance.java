package com.example.hospitalManagementSystem.ambulance.entity;

import com.example.hospitalManagementSystem.audit.Auditable;
import com.example.hospitalManagementSystem.enums.AmbulanceEnums;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "ambulance")
public class Ambulance extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vehicle_number", nullable = false, unique = true)
    private String vehicleNumber;

    @Column(name = "ambulance_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AmbulanceEnums.AmbulanceType ambulanceType; // Enum: BASIC, ICU, etc.

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private AmbulanceEnums.AmbulanceStatus ambulanceStatus; // Enum: AVAILABLE, ON_DUTY, MAINTENANCE

    @Column(name = "last_maintenance_date")
    private LocalDate lastMaintenanceDate;

}
