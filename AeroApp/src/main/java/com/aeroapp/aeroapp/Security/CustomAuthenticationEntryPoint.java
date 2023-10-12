package com.aeroapp.aeroapp.Security;


import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        // Personaliza aquí cómo manejar las respuestas de autenticación no válida
        // Por ejemplo, puedes devolver un mensaje de error o redirigir a una página de inicio de sesión.
        response.sendError(HttpServletResponse.SC_OK, "Acceso autorizado ");
    }
}