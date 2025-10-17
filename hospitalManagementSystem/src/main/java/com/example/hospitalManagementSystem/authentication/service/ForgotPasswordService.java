package com.example.hospitalManagementSystem.authentication.service;

public interface ForgotPasswordService {
    public void initiateReset(String email);
    public void validateOtp(String email, String otp);
    public void resetPassword(String email, String newPassword);

}
