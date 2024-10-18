package com.college.recipes_collection.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.college.recipes_collection.models.UserAuthentication;
import com.college.recipes_collection.repositories.UserAuthenticationRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    private UserAuthenticationRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuthentication userAuth = repository.findByUsername(username)
                                        .orElseThrow(() -> new RuntimeException("User not found"));
        
        return userAuth;
    }
    
}
