package com.college.recipes_collection.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.college.recipes_collection.exceptions.RecipeAlreadyExistsException;
import com.college.recipes_collection.models.Category;
import com.college.recipes_collection.models.Recipe;
import com.college.recipes_collection.models.User;
import com.college.recipes_collection.repositories.CategoryRepository;
import com.college.recipes_collection.repositories.RecipeRepository;
import com.college.recipes_collection.repositories.UserRepository;
import com.college.recipes_collection.requests.RecipeRequestDTO;

@Service
public class RecipeService {
    
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public RecipeService(RecipeRepository recipeRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public void createRecipe(RecipeRequestDTO request) {

        //Move to other method
        User user = userRepository.findById(request.userId())
            .orElseThrow(() -> new RuntimeException("User not found"));
        boolean exists = recipeRepository.existsByUserAndName(user, request.name());

        if(exists) {
            throw new RecipeAlreadyExistsException("The same recipe cannot be created by the same chef \nName: " + request.name() + "\nChef: " + user.getName());
        }

        //Init create
        Recipe recipe = new Recipe();
        recipe.setUser(user);
        recipe.setName(request.name());

        Category category = categoryRepository.findByName(request.name());
        recipe.setCategory(category);

        recipe.setCreatedAt(LocalDateTime.now());
        recipe.setPreparationMethod(request.preparationMethod());
        recipe.setPortions(request.portions());
        recipe.setDescription(request.description());
        recipe.setIsPublished(false);
        recipe.setIsRated(false);

        recipeRepository.save(recipe);
    }
}
