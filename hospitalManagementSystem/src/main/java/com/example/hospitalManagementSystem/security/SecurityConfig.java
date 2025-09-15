package com.example.hospitalManagementSystem.security;

import com.example.hospitalManagementSystem.authentication.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)  //@Preauthorized will stop access of unathorized user
public class SecurityConfig {
    @Autowired
    CustomSuccessHandler  customSuccessHandler;

    private final CustomUserDetailsService customUserDetailsService;




    // constructor for injection
    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    // Password Encoder
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    // 2. DaoAuthenticationProvider (connects DB users + password check)
    @Bean
    public DaoAuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;

    }

    // 3. Authentication Manager (uses the provider)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
    {
        return config.getAuthenticationManager();
    }

    //Security rules
    @Bean
    public SecurityFilterChain  securityFilterChain(HttpSecurity http) throws Exception
    {
        http.
                csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/register","/register/**","/login", "/css/**", "/js/**" ,"/images/**","/vendors/**").permitAll()

                                //admin can access all endpoint
                                .requestMatchers("/super_admin/**").hasRole("SUPER_ADMIN")

                                //admin can access all endpoint
                                .requestMatchers("/admin/**").hasRole("ADMIN")

                                //Dotor can access Ward-related /patient related endpoint
                                .requestMatchers("/doctor/**").hasRole("DOCTOR")

                                //HeadNurse can access Ward-related / related endpoint
                                .requestMatchers("/headnurse/**").hasRole("HEADNURSE")

                                // Pharmacist can access medicine-related endpoints
                                .requestMatchers("/pharmacist/**").hasRole("PHARMACIST")

                                // Accountant can access billing endpoints
                                .requestMatchers("/accountant/**").hasRole("ACCOUNTANT")

                                // HR can access employee-related endpoints
                                .requestMatchers("/hr/**").hasRole("HR")

                                // HR can access employee-related endpoints
                                .requestMatchers("/insurance/**").hasRole("INSURANCE")

                                // Laboratorist can access lab reports endpoints
                                .requestMatchers("/laboratorist/**").hasRole("LABORATORIST")
                                .anyRequest().authenticated()

                        )

                .formLogin(form -> form
                                .loginPage("/login")
                                .successHandler(customSuccessHandler)  // after login go component customsuccess handler and check role of user
                                .failureUrl("/login?error=true")    // redirect on failed login
                                .permitAll())



                .logout(logout -> logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll());


              return http.build();
    }


}
