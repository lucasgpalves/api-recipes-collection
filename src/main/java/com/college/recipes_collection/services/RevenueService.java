package com.college.recipes_collection.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.recipes_collection.dto.requests.RevenueRequestDTO;
import com.college.recipes_collection.models.Recipe;
import com.college.recipes_collection.models.Revenue;
import com.college.recipes_collection.models.User;
import com.college.recipes_collection.repositories.RecipeRepository;
import com.college.recipes_collection.repositories.RevenueRepository;
import com.college.recipes_collection.repositories.UserRepository;

@Service
public class RevenueService {
    
    @Autowired
    private RevenueRepository revenueRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    public void createRevenue(RevenueRequestDTO request) {
        Revenue revenue = new Revenue();
        saveRevenue(revenue, request);
    }


    private void saveRevenue(Revenue revenue, RevenueRequestDTO request) {
        revenue.setRating(request.rating());
        revenue.setDescription(request.description());
        revenue.setUser(findUser(request));
        revenue.setRecipe(findRecipe(request));

        if (revenue.getId() == null) {
            revenue.setCreatedAt(LocalDateTime.now());
        }

        revenueRepository.save(revenue);
    }

    private User findUser(RevenueRequestDTO request) {
        return userRepository.findById(request.userId())
            .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private Recipe findRecipe(RevenueRequestDTO request) {
        return recipeRepository.findById(request.recipeId())
            .orElseThrow(() -> new RuntimeException("Recipe not found"));
    }

}
