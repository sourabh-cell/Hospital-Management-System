package com.example.hospitalManagementSystem.authentication.service;

import com.example.hospitalManagementSystem.authentication.entity.OtpToken;
import com.example.hospitalManagementSystem.authentication.entity.UserEntity;
import com.example.hospitalManagementSystem.authentication.repositories.OtpTokenRepo;
import com.example.hospitalManagementSystem.authentication.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService{
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private OtpTokenRepo  otpTokenRepo;

    @Autowired
    private EmailService  emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

// for initiating password reset functionality

    @Transactional
    @Override
    public void initiateReset(String email)
    {
        System.out.println("initiating reset");
        UserEntity user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email not registered"));

        // Generating random OTP
        String otp = String.valueOf(new Random().nextInt(900000) + 100000);
        LocalDateTime expiry = LocalDateTime.now().plusMinutes(10);
        System.out.println("Reset otp: " + otp);
        // Clean old tokens
        otpTokenRepo.deleteByEmail(email);

        OtpToken token = new OtpToken();
        token.setEmail(email);
        token.setOtp(otp);
        token.setExpiry(expiry);
        otpTokenRepo.save(token);

        emailService.sendOtpEmail(email, otp);

    }

    @Override
    public void validateOtp(String email, String otp)
    {
        // We are accessing otp from table which are having used field as false
        OtpToken token = otpTokenRepo.findByEmailAndOtpAndUsedFalse(email, otp)
                .orElseThrow(() -> new RuntimeException("Invalid OTP"));

        if (token.getExpiry().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP expired");
        }

        // setting used field as true
        token.setUsed(true);
        otpTokenRepo.save(token);

    }


    @Override
    public void resetPassword(String email, String newPassword)
    {
        UserEntity user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepo.save(user);
    }


}

