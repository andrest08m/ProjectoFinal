package com.aeroapp.aeroapp.Security.Auth;




import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.aeroapp.aeroapp.Repository.UserClientRepository;
import com.aeroapp.aeroapp.models.UserClient;
import com.aeroapp.aeroapp.utils.TokenUtils;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserClientRepository userClientRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthResponse register(AuthCredentials request) {
        UserClient user = UserClient.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .roleName(request.getRoles())
                .authorities(request.getAuthorities())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userClientRepository.save(user);

        String token = TokenUtils.createToken(request.getUsername(),request.getEmail());
        return AuthResponse.builder()
                .token(token)
                .email(request.getEmail())
                .username(request.getUsername())
                .roles(request.getRoles())
                .build();
    }

    public AuthResponse login(AuthCredentials request) {

        String usernameOrEmail;
        if (request.getUsername()!=null){ usernameOrEmail = request.getUsername();}
        else {usernameOrEmail = request.getEmail();}

        UserClient user = userClientRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }
        // Generar un token JWT y devolverlo en la respuesta
        String token = TokenUtils.createToken(request.getUsername(),request.getEmail());
        return AuthResponse.builder()
                .token(token)
                .email(request.getEmail())
                .username(request.getUsername())
                .roles(request.getRoles())
                .build();
    }

}