package com.example.hospitalManagementSystem.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;


    public void sendOtpEmail(String toEmail, String otp) {
        // Setting email server
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(" Password Reset OTP");
        message.setText("Your One-Time Password (OTP) for resetting your password for HarishChandra Medicity is:" + otp + "\nThis OTP is valid for 10 minutes. Please do not share this code with anyone for security reasons");
        mailSender.send(message);
    }


}
