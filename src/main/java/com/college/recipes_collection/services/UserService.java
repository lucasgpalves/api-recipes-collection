package com.college.recipes_collection.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.recipes_collection.exceptions.RoleNotFoundException;
import com.college.recipes_collection.models.Role;
import com.college.recipes_collection.models.User;
import com.college.recipes_collection.repositories.RoleRepository;
import com.college.recipes_collection.repositories.UserRepository;
import com.college.recipes_collection.requests.UserRequestDTO;
import com.college.recipes_collection.responses.UserResponseDTO;

@Service
public class UserService {
    
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    public void createUser(UserRequestDTO request) {
        User createdUser = new User();

        createdUser.setCpf(request.cpf());
        createdUser.setName(request.name());
        createdUser.setIngressedAt(LocalDateTime.now());
        createdUser.setSalary(request.salary());
        
        Role role = roleRepository.findByName(request.roleName());
        if(role == null){
            throw new RoleNotFoundException("Role not found: " + request.roleName());
        } else {
            createdUser.setRole(role);
        }

        userRepository.save(createdUser);
    }

    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));

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

    public UserResponseDTO updateUser(Long id, UserRequestDTO request) {
        User updatedUser = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));

        
        updatedUser.setCpf(request.cpf());
        updatedUser.setName(request.name());
        updatedUser.setSalary(request.salary());
        
        Role role = roleRepository.findByName(request.roleName());
        if(role == null){
            updatedUser.setRole(role);
        } else {
            throw new RoleNotFoundException("Role not found: " + request.roleName());
        }

        User savedUser = userRepository.save(updatedUser);

        return new UserResponseDTO(
            savedUser.getCpf(), 
            savedUser.getName(), 
            savedUser.getRole() == null ? null : savedUser.getRole().getName(), 
            savedUser.getSalary(), 
            savedUser.getFantasyName()
        );
    }

    public void deleteUser(Long id) {
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("User not found");
        }
    }
}
