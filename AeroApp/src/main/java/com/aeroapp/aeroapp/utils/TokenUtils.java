package com.aeroapp.aeroapp.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {

    private final static String ACCESS_TOKEN_SECRET = "0a5954de627cfce3adf55" +
            "573e00a67d48d0a5954de627cfce3adf55573e00a67d48d0a5954de627cfce3adf55573" +
            "e00a67d48d0a5954de627cfce3adf55573e00a67d48d0a5954de627cfce3adf55573e" +
            "00a67d48d";

    private final static Long ACCESS_TOKEN_VALIDITY_SECONDS =2_592_000L;

    public static String createToken (String username, String email) {
        long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1000;
        Date expirationDate = new Date(System.currentTimeMillis()+expirationTime);

        Map<String,Object> extra = new HashMap<>();
        extra.put("nombre", username);

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(getKey(),SignatureAlgorithm.HS256)
                .compact();
    }

    private static Key getKey() {
        byte[] keyBytes= Decoders.BASE64.decode(ACCESS_TOKEN_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public  static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {
            Claims claims=Jwts.parser()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .parseClaimsJws(token)
                    .getBody();

            String email = claims.getSubject();

            return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
        } catch (JwtException e) {
            return null;
        }
    }

}