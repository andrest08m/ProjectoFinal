package com.aeroapp.aeroapp.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {


    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private CustomerDetailsService customerDetailsService;

    private  String username = null;

    Claims claims  = null;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
    if(request.getServletPath().matches("/user/login")){
        filterChain.doFilter(request,response);
    }else {
        String authorizationHeader = request.getHeader("autorization");
        String token = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            token = authorizationHeader.substring(7);
            username = jwtUtils.extracUsername(token);
            claims = jwtUtils.extracAllClaims(token);
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = customerDetailsService.loadUserByUsername(username);
            if (jwtUtils.validateToken(token,userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null ,userDetails.getAuthorities());
                new WebAuthenticationDetailsSource().buildDetails(request);
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

        }
        filterChain.doFilter(request,response);
    }
    }

    public Boolean isAdmin(){
        return  "admin".equalsIgnoreCase((String)claims.get("role"));
    }

    public Boolean isUser(){
        return  "user".equalsIgnoreCase((String) claims.get("role"));

    }


    public String getcurrentUser(){

        return username;
    }



}
