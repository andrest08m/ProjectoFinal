package com.aeroapp.aeroapp.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;




@Configuration
public class SecurityConfiguration {

    @Autowired
    CustomUserDetailService customUserDetailService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(8);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeHttpRequests((authorize) -> {
                    authorize.antMatchers("/public/**").permitAll();
                })
                .authorizeHttpRequests((authorize) -> {
                    authorize.antMatchers("/asistencias").hasRole("ADMIN");
                })
                .authorizeHttpRequests((authorize) -> {
                    authorize.antMatchers("/verify").hasRole("USER");
                })
                .authorizeHttpRequests((authorize) -> {
                    authorize.anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults())
        ;
        return httpSecurity.build();
    }




}
