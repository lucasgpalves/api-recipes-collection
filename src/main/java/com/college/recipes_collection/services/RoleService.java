package com.college.recipes_collection.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.recipes_collection.dto.responses.RoleResponseDTO;
import com.college.recipes_collection.models.Role;
import com.college.recipes_collection.repositories.RoleRepository;
import com.college.recipes_collection.dto.requests.RoleRequestDTO;

@Service
public class RoleService {
    
    @Autowired
    private RoleRepository roleRepository;

    public void createRole(RoleRequestDTO request) {
        Role role = new Role();
        role.setName(request.name());
        roleRepository.save(role);
    }

    public RoleResponseDTO getRoleById(int id) {
        Role role = roleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Role not found"));
        
        return new RoleResponseDTO(
            role.getId(),
            role.getName()
        );
    }

    public List<RoleResponseDTO> getAllRoles() {
        return roleRepository.findAll().stream()
            .map(role -> new RoleResponseDTO(
                role.getId(), 
                role.getName()
            ))
            .collect(Collectors.toList());
    }

    public void updateRoleById(int id, RoleRequestDTO request) {
        Role updatedRole = roleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Role not found"));

        updatedRole.setName(request.name());

        roleRepository.save(updatedRole);
    }

    public void deleteRoleById(int id) {
        if(roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
        } else {
            throw new RuntimeException("Role not found");
        }
    }
}
