package com.college.recipes_collection.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.recipes_collection.dto.responses.UserResponseDTO;
import com.college.recipes_collection.exceptions.RoleNotFoundException;
import com.college.recipes_collection.models.Role;
import com.college.recipes_collection.models.User;
import com.college.recipes_collection.repositories.RoleRepository;
import com.college.recipes_collection.repositories.UserRepository;
import com.college.recipes_collection.dto.requests.UserRequestDTO;

@Service
public class UserService {
    
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    public void createUser(UserRequestDTO request) {
        User createdUser = new User();
        saveUser(createdUser, request);
    }

    public UserResponseDTO getUserById(Long id) {
        User user = verifyIsUserExists(id);

        return new UserResponseDTO(
            user.getCpf(), 
            user.getName(), 
            user.getRole() == null ? null : user.getRole().getName(), 
            user.getSalary(), 
            user.getFantasyName()
        );
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
            .map(user -> new UserResponseDTO(
                user.getCpf(), 
                user.getName(), 
                user.getRole() == null ? null : user.getRole().getName(), 
                user.getSalary(), 
                user.getFantasyName()
            ))
            .collect(Collectors.toList());
    }

    public void updateUserById(Long id, UserRequestDTO request) {
        User updatedUser = verifyIsUserExists(id);
        saveUser(updatedUser, request);
    }

    public void terminateUserById(Long id) {
        User user = verifyIsUserExists(id);
        user.setTerminatedAt(LocalDateTime.now());
        userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        if(userRepository.existsById(id)) {
            boolean hasRelatedEntities = userHasRelatedEntities(id);

            if (!hasRelatedEntities) {
                userRepository.deleteById(id);
            } else {
                throw new RuntimeException("This user has some related entitie");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }

    private User saveUser(User user, UserRequestDTO request) {
        user.setCpf(request.cpf());
        user.setName(request.name());
        user.setSalary(request.salary());

        if (user.getId() == null) {
            user.setIngressedAt(LocalDateTime.now());
        }

        Role role = findByName(request.roleName());
        if(role == null){
            throw new RoleNotFoundException("Role not found: " + request.roleName());
        } else {
            user.setRole(role);
        }

        return userRepository.save(user);
    }

    private User verifyIsUserExists(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private boolean userHasRelatedEntities(Long id) {
        return userRepository.hasRelatedRecipes(id) ||
                userRepository.hasRelatedReviews(id) ||
                userRepository.hasRelatedBooks(id);
    }

    private Role findByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }
}
