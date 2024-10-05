package com.college.recipes_collection.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.college.recipes_collection.models.Recipe;
import com.college.recipes_collection.models.User;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>{
    Boolean existsByUserAndName(User user, String name);

    @Query("SELECT r FROM Recipe r WHERE r.user.id = :userId AND FUNCTION('MONTH', r.createdAt) = :month AND FUNCTION('YEAR', r.createdAt) = :year")
    List<Recipe> findRecipesByUserAndMonth(@Param("userId") Long userId, @Param("month") int month, @Param("year") int year);
}
