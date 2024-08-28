package com.college.recipes_collection.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.recipes_collection.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
    Role findByName(String roleName);
}
