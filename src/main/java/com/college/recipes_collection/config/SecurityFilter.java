package com.college.recipes_collection.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.college.recipes_collection.repositories.UserAuthenticationRepository;
import com.college.recipes_collection.services.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{
    
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserAuthenticationRepository userAuthenticationRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recoverToken(request);

        System.out.println("TOKEN = " + token);

        if (token != null) {
            String login = tokenService.validateToken(token);

            System.out.println("LOGIN = " + login);

            UserDetails user = findByUsername(login);
            System.out.println(user.getUsername() + " " + user.getAuthorities());

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } 
        filterChain.doFilter(request, response);

    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }

    private UserDetails findByUsername(String login) {
        return userAuthenticationRepository.findByUsername(login)
        .orElseThrow(() -> new RuntimeException("User not found"));
    }

}
