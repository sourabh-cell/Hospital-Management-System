package com.example.hospitalManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Users entity
 * Stores authentication details like username, email, password.
 * Each User can have multiple Roles and one UserInfo.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    private Boolean enabled = true;

    private LocalDateTime createdAt;
    private String createdBy;

    private LocalDateTime modifiedAt;
    private String modifiedBy;

    /** Many-to-Many with Role */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "User_Role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    /** One-to-One with UserInfo */
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL,optional = true)
    private UserInfo userInfo;

    // getters & setters
}