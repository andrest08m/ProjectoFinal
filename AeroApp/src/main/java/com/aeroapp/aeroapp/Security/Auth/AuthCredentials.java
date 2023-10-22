package com.aeroapp.aeroapp.Security.Auth;

import com.aeroapp.aeroapp.models.Authority;
import com.aeroapp.aeroapp.utils.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthCredentials {

    private String username;
    private String email;
    private String password;
    private RoleName roles;
    private List<Authority> authorities;
}