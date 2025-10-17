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



        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String redirectUrl = "/dashboard/default"; // fallback



        if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_SUPER_ADMIN"))) {
            redirectUrl = "/dashboard/admin";
        } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            redirectUrl = "/dashboard/admin";
        } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_DOCTOR"))) {
            redirectUrl = "/dashboard/doctor";
        } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_HEADNURSE"))) {
            redirectUrl = "/dashboard/headnurse";
        } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_PHARMACIST"))) {
            redirectUrl = "/dashboard/pharmacist";
        } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ACCOUNTANT"))) {
            redirectUrl = "/dashboard/accountant";
        } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_HR"))) {
            redirectUrl = "/dashboard/hr";
        } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_LABORATORIST"))) {
            redirectUrl = "/dashboard/laboratorist";
        } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_INSURANCE"))) {
            redirectUrl = "/dashboard/insurance";
        } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_RECEPTIONIST"))) {
            redirectUrl = "/dashboard/receptionist";
        }


        response.sendRedirect(redirectUrl);
    }
}
