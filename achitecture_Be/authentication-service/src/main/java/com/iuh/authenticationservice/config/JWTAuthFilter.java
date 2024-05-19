package com.iuh.authenticationservice.config;


import com.iuh.authenticationservice.service.JWTUtils;
import com.iuh.authenticationservice.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {

    @Autowired
    private  JWTUtils jwtUtils;
    @Autowired
    private UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        final String authHeader = request.getHeader("Authorization");
        String jwtToken ;
        String userName;
        if (authHeader == null || authHeader.isBlank()){
            filterChain.doFilter(request,response);
            return;
        }
        jwtToken = authHeader.substring(7);
        System.out.println("doFilterInternal jwtToken: "+jwtToken);
        userName = jwtUtils.extractUserName(jwtToken);
        System.out.println("doFilterInternal userName: "+userName);
        if (userName != null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = userService.loadUserByUsername(userName);
            if (jwtUtils.isTokenvalid(jwtToken,userDetails)){
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                System.out.println("securityContext: "+securityContext);
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        userDetails,null,userDetails.getAuthorities()
                );
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                securityContext.setAuthentication(token);
                SecurityContextHolder.setContext(securityContext);
            }
        }
        filterChain.doFilter(request,response);
    }
}