package com.aeroapp.aeroapp.Security.Auth;

import com.aeroapp.aeroapp.utils.RoleName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String token;
    private String username;
    private String email;
    private RoleName roles;

}
