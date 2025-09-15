package com.example.hospitalManagementSystem.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler
{

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException
    {


        System.out.println("Authorities:");
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            System.out.println(" - " + authority.getAuthority());
        }

        //check role of user
        String redirectUrl = request.getContextPath();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        // Check for roles only
        if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_SUPER_ADMIN"))) {
            redirectUrl = "/super_admin";
        } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            redirectUrl = "/admin";
        } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_DOCTOR"))) {
            redirectUrl = "/doctor";
        } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_HEADNURSE"))) {
            redirectUrl = "/headnurse";
        } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_PHARMACIST"))) {
            redirectUrl = "/pharmacist";
        } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ACCOUNTANT"))) {
            redirectUrl = "/accountant";
        } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_HR"))) {
            redirectUrl = "/hr";
        } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_LABORATORIST"))) {
            redirectUrl = "/laboratorist";
        } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_INSURANCE"))) {
            redirectUrl = "/insurance";
        } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_RECEPTIONIST"))) {
            redirectUrl = "/receptionist";
        }
        response.sendRedirect(redirectUrl);
    }
}
