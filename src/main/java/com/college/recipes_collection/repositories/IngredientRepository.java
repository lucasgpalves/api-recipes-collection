package com.college.recipes_collection.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.recipes_collection.models.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer>{
   
}
