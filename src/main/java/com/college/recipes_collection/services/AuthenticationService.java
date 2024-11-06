package com.college.recipes_collection.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.college.recipes_collection.dto.requests.LoginRequestDTO;
import com.college.recipes_collection.dto.requests.RegisterRequestDTO;
import com.college.recipes_collection.dto.responses.TokenResponseDTO;
import com.college.recipes_collection.models.User;
import com.college.recipes_collection.models.UserAuthentication;
import com.college.recipes_collection.repositories.UserAuthenticationRepository;
import com.college.recipes_collection.repositories.UserRepository;

@Service
public class AuthenticationService {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAuthenticationRepository userAuthenticationRepository;

    @Autowired
    private TokenService tokenService;

    public TokenResponseDTO login(LoginRequestDTO request) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(request.username(), request.password());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        String token = tokenService.generateToken((UserAuthentication) auth.getPrincipal());
        return new TokenResponseDTO(token);
    }

    public boolean register(RegisterRequestDTO request) {
        Optional<UserAuthentication> userAuthExisting = findByUsername(request.username());
        if (userAuthExisting.isPresent()) return false;

        String encryptedPassword = new BCryptPasswordEncoder().encode(request.password());

        User user = findById(request.userId());
        UserAuthentication aux = userAuthenticationRepository.findByUsername(request.username())
                                    .orElseThrow(() -> new RuntimeException());

        UserAuthentication userAuth = new UserAuthentication(aux.getId(), request.username(), encryptedPassword, true, user);

        userAuthenticationRepository.save(userAuth);
        return true;
    }

    private User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    private Optional<UserAuthentication> findByUsername(String username) {
        return userAuthenticationRepository.findByUsername(username);
    }
}
