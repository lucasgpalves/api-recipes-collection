package com.college.recipes_collection.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.college.recipes_collection.models.Ingredient;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer>{
   Optional<Ingredient> findByName(String name);
}
