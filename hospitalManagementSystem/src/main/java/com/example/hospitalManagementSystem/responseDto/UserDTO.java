package com.example.hospitalManagementSystem.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO for transferring user data without exposing password.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
    private Long userId;
    private String username;
    private String email;
    private Boolean enabled;
    private Set<String> roles; // Only role names, not whole entity
    private LocalDateTime createdAt;


}