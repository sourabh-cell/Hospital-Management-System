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


        //check role of user
        String redirectUrl = request.getContextPath();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities)
        {
            String role = grantedAuthority.getAuthority(); //putting role of user in role variable

            if (role.equals("ROLE_ADMIN"))  //checking role of user
            {
                redirectUrl = "/admin/";
            }

            else if (role.equals("ROLE_DOCTOR"))  // checking role of user
            {
                redirectUrl = "/doctor";
            }

            else if (role.equals("ROLE_HEADNURSE"))  // checking role of user
            {
                redirectUrl = "/headnurse";
            }

            else if (role.equals("ROLE_PHARMACIST"))  // checking role of user
            {
                redirectUrl = "/pharmacist";
            }

            else if (role.equals("ROLE_ACCOUNTANT"))  // checking role of user
            {
                redirectUrl = "/accountant";
            }

            else if (role.equals("ROLE_HR"))  // checking role of user
            {
                redirectUrl = "/hr";
            }

            else if (role.equals("ROLE_LABORATORIST"))  // checking role of user
            {
                redirectUrl = "/laboratorist";
            }

            else if (role.equals("ROLE_INSURANCE"))  // checking role of user
            {
                redirectUrl = "/insurance";
            }
        }
        response.sendRedirect(redirectUrl);
    }
}
