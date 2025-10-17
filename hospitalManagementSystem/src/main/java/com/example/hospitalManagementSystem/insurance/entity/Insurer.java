package com.example.hospitalManagementSystem.insurance.entity;


import com.example.hospitalManagementSystem.authentication.entity.UserEntity;
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
@Table(name = "insurer")
public class Insurer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "experience", nullable = false)
    private String experience;

    @Column(name = "qualifications", nullable = false)
    private String qualifications;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;




}
