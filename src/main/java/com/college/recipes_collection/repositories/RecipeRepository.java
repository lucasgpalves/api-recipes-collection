package com.college.recipes_collection.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.college.recipes_collection.models.Recipe;
import com.college.recipes_collection.models.User;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>{
    Boolean existsByUserAndName(User user, String name);
}
