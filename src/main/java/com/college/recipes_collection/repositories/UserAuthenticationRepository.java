package com.college.recipes_collection.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.college.recipes_collection.models.UserAuthentication;

@Repository
public interface UserAuthenticationRepository extends JpaRepository<UserAuthentication, Long>{
    Optional<UserAuthentication> findByUsername(String username);
}
