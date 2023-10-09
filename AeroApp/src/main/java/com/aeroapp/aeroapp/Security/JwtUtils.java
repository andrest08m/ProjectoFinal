package com.aeroapp.aeroapp.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtUtils {

    public String secret = "Springboot";


    public String extracUsername(String token){

        return null;
    }

    public Date extractExpiration(String token){
        return extracClaims(token, Claims ::getExpiration);

    }

  public <T> T  extracClaims(String token, Function<Claims,T> claimsResolver){
            final Claims claims = extracAllClaims(token);
            return claimsResolver.apply(claims);
  }


public Claims extracAllClaims (String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

}
private Boolean isTokenExpiration(String token){
        return extractExpiration(token).before(new Date());
     }

     public String generayeToken(String username,String role){
         Map<String,Object> claims = new HashMap<>();
         claims.put("role",role);
         return createToken(claims,username);
     }

     private String createToken(Map<String,Object> claims,String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 100 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256,secret).compact();
     }

     public Boolean validateToken(String token, UserDetails userDetails){
        final  String username = extracUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpiration(token);
     }




}
