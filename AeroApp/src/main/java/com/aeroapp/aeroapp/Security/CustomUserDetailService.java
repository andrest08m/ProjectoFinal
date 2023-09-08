package com.aeroapp.aeroapp.Security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomUserDetailService implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Agregamos lógica de cargar la información del usuario desde la base de datos.
        System.out.println("se inicio sesion con" + username);
        if (!username.equals("Andres")) {
            throw new UsernameNotFoundException("Usuario " + username + " no existe");

        }
          return User.withUsername(username)
                  // CUANDO CONSULTEN DE LA BASE DE DATOS NO ENCRIPTEN DOS VECES
                  .password(passwordEncoder().encode(username))
                  .roles("ADMIN")
                  .build();

    }
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(8);
    }
}