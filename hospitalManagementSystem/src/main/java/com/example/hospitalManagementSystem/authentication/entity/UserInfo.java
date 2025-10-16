package com.example.hospitalManagementSystem.authentication.entity;

import com.example.hospitalManagementSystem.audit.Auditable;
import com.example.hospitalManagementSystem.enums.Enums;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * UserInfo entity
 * Stores personal details of users.
 * Linked to both User and Address entities.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Users_Info")
public class UserInfo extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** One-to-One with User */
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    /** One-to-One with Address */
    @OneToOne
    @JoinColumn(name = "address_id", unique = true)
    private Address address;

    private String firstName;
    private String lastName;
    private String mobileNumber;

    @Lob
    private byte[] profilePic;

    @Enumerated(EnumType.STRING)
    private Enums.Gender gender;

    private LocalDate dob;
    private Integer age;
    private LocalDate joiningDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "blood_group", nullable = false)
    private Enums.BloodGroup bloodGroup;

    private String idProofName;

    @Lob
    private byte[] idProofPic;







    // getters & setters
}