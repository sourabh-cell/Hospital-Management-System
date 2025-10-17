package com.example.hospitalManagementSystem.authentication.repositories;

import com.example.hospitalManagementSystem.authentication.entity.OtpToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpTokenRepo extends JpaRepository<OtpToken, Long> {
    Optional<OtpToken> findByEmailAndOtpAndUsedFalse(String email, String otp);
    void deleteByEmail(String email); // Optional use for cleanup table

}
