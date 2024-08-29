package com.college.recipes_collection.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.college.recipes_collection.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
    Optional<Category> findByName(String name);
}
