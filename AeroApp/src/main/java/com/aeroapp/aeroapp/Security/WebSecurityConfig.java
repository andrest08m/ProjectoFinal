package com.aeroapp.aeroapp.Security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@AllArgsConstructor
public class WebSecurityConfig {



    @Bean
    UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager Manager = new InMemoryUserDetailsManager();
        Manager.createUser(User.withUsername("Admin")
                .password(passwordEncoder().encode("Admin"))
                .roles()
                .build());
        return Manager;
    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();

    }



}