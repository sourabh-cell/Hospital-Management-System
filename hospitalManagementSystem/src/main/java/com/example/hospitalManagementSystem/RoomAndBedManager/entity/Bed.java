package com.example.hospitalManagementSystem.RoomAndBedManager.entity;

import com.example.hospitalManagementSystem.audit.Auditable;

import com.example.hospitalManagementSystem.enums.Enums;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "beds")
public class Bed extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bed_id")
    private Long id;

    @Column(name = "bed_number")
    private String bedNumber;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;


    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Enums.BedStatus status;



}
