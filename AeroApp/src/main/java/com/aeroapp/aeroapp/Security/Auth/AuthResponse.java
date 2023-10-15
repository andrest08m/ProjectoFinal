package com.aeroapp.aeroapp.Security.Auth;

import com.aeroapp.aeroapp.utils.RoleName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class AuthResponse {

    private String token;
    private String username;
    private String email;
    private RoleName roles;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleName getRoles() {
        return roles;
    }

    public void setRoles(RoleName roles) {
        this.roles = roles;
    }

    public AuthResponse(String token, String username, String email, RoleName roles) {
        this.token = token;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
