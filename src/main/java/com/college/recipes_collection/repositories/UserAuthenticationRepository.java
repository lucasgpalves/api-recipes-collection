package com.college.recipes_collection.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.recipes_collection.models.UserAuthentication;

public interface UserAuthenticationRepository extends JpaRepository<UserAuthentication, String>{
    Optional<UserAuthentication> findByUsername(String username);
}
