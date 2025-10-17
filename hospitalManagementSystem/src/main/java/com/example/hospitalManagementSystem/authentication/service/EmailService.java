package com.example.hospitalManagementSystem.authentication.service;

public interface EmailService {
    public void sendOtpEmail(String toEmail, String otp);
}
