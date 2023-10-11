package com.aeroapp.aeroapp.Security;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class WebSecurityConifg {

    private final  UserDetailsService userDetailsService;
    private JWTAuthorizationFilter jwtAuthorizationFilter;

    @Autowired
    public void WebSecurityConfig(JWTAuthorizationFilter jwtAuthorizationFilter) {
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    }

    public WebSecurityConifg(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    SecurityFilterChain filterChain (HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
       JWTAutenticationFilter jwtAutenticationFilter = new JWTAutenticationFilter();
       jwtAutenticationFilter.setAuthenticationManager(authenticationManager);
       jwtAutenticationFilter.setFilterProcessesUrl("/login");



        return http
                .csrf().disable()
                .authorizeHttpRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(jwtAutenticationFilter)
                .addFilterBefore(jwtAutenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
//    @Bean
//    UserDetailsService userDetailsService(){
//        InMemoryUserDetailsManager Manager = new InMemoryUserDetailsManager();
//        Manager.createUser(User.withUsername("Admin")
//                .password(passwordEncoder().encode("Admin"))
//                .roles()
//                .build());
//        return Manager;
//


    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
       return http.getSharedObject(AuthenticationManagerBuilder.class)
               .userDetailsService(userDetailsService)
               .passwordEncoder(passwordEncoder)
               .and()
               .build();

    }


    
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();


    }

    public static void main(String[] args) {
        System.out.printf("pass: " + new BCryptPasswordEncoder().encode("andres"));
    }
    
}
