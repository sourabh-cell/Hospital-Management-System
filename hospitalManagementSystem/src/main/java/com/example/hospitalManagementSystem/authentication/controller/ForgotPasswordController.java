package com.example.hospitalManagementSystem.authentication.controller;

import com.example.hospitalManagementSystem.authentication.dto.ForgotPasswordRequestDto;
import com.example.hospitalManagementSystem.authentication.service.ForgotPasswordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ForgotPasswordController {

    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @GetMapping("/forgot-password")
    public String showForgotPasswordPage( Model model) {
        System.out.println(1);

        // Always ensure DTO is present
        if (!model.containsAttribute("forgotPasswordRequestDto")) {
            model.addAttribute("forgotPasswordRequestDto", new ForgotPasswordRequestDto());
        }

        // Do NOT override otpPhase/resetPhase unless they're missing
        if (!model.containsAttribute("otpPhase")) {
            model.addAttribute("otpPhase", false);
        }
        if (!model.containsAttribute("resetPhase")) {
            model.addAttribute("resetPhase", false);
        }


        return "forgot_password";
    }

// for initiating password reset request
    @PostMapping("/request")
    public String requestOtp(@Valid @ModelAttribute("forgotPasswordRequestDto") ForgotPasswordRequestDto dto,
                             BindingResult result, Model model,RedirectAttributes redirectAttributes)
    {

        if (result.hasErrors()) {
            model.addAttribute("forgotPasswordRequestDto", dto);
            return "redirect:/forgot-password";
        }

        try {
            System.out.println("in request");
            forgotPasswordService.initiateReset(dto.getEmail());
            redirectAttributes.addFlashAttribute("forgotPasswordRequestDto", dto);
            redirectAttributes.addFlashAttribute("otpPhase", true);
            redirectAttributes.addFlashAttribute("message", "OTP sent to your email.");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            redirectAttributes.addFlashAttribute("otpPhase", false); //
            redirectAttributes.addFlashAttribute("resetPhase", false);
            redirectAttributes.addFlashAttribute("forgotPasswordRequestDto", dto);
        }
        System.out.println("exiting request");
        return "redirect:/forgot-password";
    }


    // for resend OTP
    @PostMapping("/resend-otp")
    public String resendOtp(@ModelAttribute("forgotPasswordRequestDto") ForgotPasswordRequestDto dto,
                            RedirectAttributes redirectAttributes) {
        try {
            System.out.println(1000);
            forgotPasswordService.initiateReset(dto.getEmail());
            redirectAttributes.addFlashAttribute("forgotPasswordRequestDto", dto);
            redirectAttributes.addFlashAttribute("otpPhase", true);
            redirectAttributes.addFlashAttribute("message", "OTP resent to your email.");
            System.out.println(2000);
        } catch (Exception e) {
            System.out.println(3000);
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            redirectAttributes.addFlashAttribute("otpPhase", true);
            redirectAttributes.addFlashAttribute("forgotPasswordRequestDto", dto);
        }
        return "redirect:/forgot-password";
    }



    // For OTP validation
    @PostMapping("/validate")
    public String validateOtp(@Valid @ModelAttribute("forgotPasswordRequestDto") ForgotPasswordRequestDto dto,
                              BindingResult result,Model model,
                              RedirectAttributes redirectAttributes) {


        if (result.hasErrors()) {
            System.out.println(400);
            model.addAttribute("forgotPasswordRequestDto", dto);
            model.addAttribute("otpPhase", true); // ✅ use model, not redirectAttributes
            model.addAttribute("resetPhase", false);
            System.out.println(500);
            return "forgot_password"; // ✅ return view directly

        }

        try {
            System.out.println(5);
            forgotPasswordService.validateOtp(dto.getEmail(), dto.getOtp());
            //redirectAttributes.addFlashAttribute("email", dto.getEmail());
            redirectAttributes.addFlashAttribute("forgotPasswordRequestDto", dto);
            redirectAttributes.addFlashAttribute("resetPhase", true);
            redirectAttributes.addFlashAttribute("message", "OTP validated. Please enter new password.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            redirectAttributes.addFlashAttribute("forgotPasswordRequestDto", dto);
            model.addAttribute("otpPhase", true);
            //redirectAttributes.addFlashAttribute("email", dto.getEmail());
        }
        System.out.println(700);
        return "redirect:/forgot-password";
    }

// For resetting password
@PostMapping("/reset")
public String resetPassword(@Valid @ModelAttribute("forgotPasswordRequestDto") ForgotPasswordRequestDto dto,
                            BindingResult result, Model model,
                            RedirectAttributes redirectAttributes) {


    if (result.hasErrors()) {
        System.out.println(40);
        model.addAttribute("forgotPasswordRequestDto", dto);
        model.addAttribute("resetPhase", true);
        model.addAttribute("otpPhase", false);// use model, not redirectAttributes
        return "forgot_password"; // return view directly, not redirect

    }


    if (!dto.getNewPassword().equals(dto.getConfirmPassword())) {
        System.out.println(50);
        model.addAttribute("forgotPasswordRequestDto", dto);
        model.addAttribute("resetPhase", true);
        model.addAttribute("otpPhase", false);
        model.addAttribute("error", "Passwords do not match.");
        return "forgot_password";

    }

    try {
        System.out.println(21);
        forgotPasswordService.resetPassword(dto.getEmail(), dto.getNewPassword());
        System.out.println(22);
        redirectAttributes.addFlashAttribute("message", "Password reset successful. You may now log in.");
        return "redirect:/login";
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute("error", e.getMessage());
        redirectAttributes.addFlashAttribute("resetPhase", true);
        redirectAttributes.addFlashAttribute("forgotPasswordRequestDto", dto);
        return "redirect:/forgot-password";

    }

}



}


