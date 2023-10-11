package com.aeroapp.aeroapp.Security;

import com.aeroapp.aeroapp.Entity.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.mapping.Collection;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
public class JWTAutenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        AutheCredentials autheCredentialss;
        try {
            autheCredentialss = new ObjectMapper().readValue(request.getReader(), AutheCredentials.class);
        } catch (IOException e) {
            // Maneja la excepción apropiadamente, esto es solo un ejemplo
            throw new BadCredentialsException("Error al leer las credenciales", e);
        }

        // Crea el token con las credenciales en el orden correcto
        UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
                autheCredentialss.getEmail(),
                autheCredentialss.getPassword(),
                Collections.emptyList()
        );

        return getAuthenticationManager().authenticate(usernamePAT);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {

        UserDetailServiceImpl userDetailService = (UserDetailServiceImpl) authResult.getPrincipal();
        String token = TokenUtils.createToken("Authorizaton", "andre");

        response.addHeader("Authorization", "Bearer " + token);
        response.getWriter().flush();

        super.successfulAuthentication(request, response, chain, authResult);
    }
}
