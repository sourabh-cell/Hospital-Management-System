package com.example.hospitalManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Address entity
 * Stores user address details.
 * One address can be linked to one UserInfo.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String addressLine1;
    private String addressLine2;
    private String city;
    private String district;
    private String state;
    private String country;
    private String pincode;

    /** One-to-One with UserInfo */
    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
    private UserInfo userInfo;

    // getters & setters
}