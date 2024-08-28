package com.college.recipes_collection.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.college.recipes_collection.requests.RoleRequestDTO;
import com.college.recipes_collection.responses.RoleResponseDTO;
import com.college.recipes_collection.services.RoleService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/roles")
public class RoleController {
    
    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody RoleRequestDTO request) {
        roleService.createRole(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleResponseDTO> getRoleById(@PathVariable int id) {
        RoleResponseDTO role = roleService.getRoleById(id);
        return ResponseEntity.ok(role);
    }

    @GetMapping
    public ResponseEntity<List<RoleResponseDTO>> getAllRoles() {
        List<RoleResponseDTO> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @PutMapping("{/id}")
    public ResponseEntity<RoleResponseDTO> updateRole(@PathVariable int id, @RequestBody RoleRequestDTO request) {
        RoleResponseDTO updatedRole = roleService.updateRole(id, request);
        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable int id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
