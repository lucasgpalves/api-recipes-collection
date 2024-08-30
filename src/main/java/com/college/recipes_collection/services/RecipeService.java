package com.college.recipes_collection.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.college.recipes_collection.dto.RecipeVerificationResult;
import com.college.recipes_collection.dto.responses.RecipeResponseDTO;
import com.college.recipes_collection.exceptions.RecipeAlreadyExistsException;
import com.college.recipes_collection.models.Category;
import com.college.recipes_collection.models.Recipe;
import com.college.recipes_collection.models.User;
import com.college.recipes_collection.repositories.CategoryRepository;
import com.college.recipes_collection.repositories.RecipeRepository;
import com.college.recipes_collection.repositories.UserRepository;
import com.college.recipes_collection.dto.requests.RecipeRequestDTO;

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

        RecipeVerificationResult result = checkIfSameRecipeExistsForUser(request.userId(), request.name());

        Recipe recipe = new Recipe();
        recipe.setUser(result.getUser());
        recipe.setName(request.name());

        Category category = findCategoryByName(request.categoryName());
        recipe.setCategory(category);

        recipe.setCreatedAt(LocalDateTime.now());
        recipe.setPreparationMethod(request.preparationMethod());
        recipe.setPortions(request.portions());
        recipe.setDescription(request.description());
        recipe.setIsPublished(false);
        recipe.setIsRated(false);

        recipeRepository.save(recipe);
    }

    public List<RecipeResponseDTO> getAllRecipes() {
        return recipeRepository.findAll().stream()
            .map(recipe -> new RecipeResponseDTO(
                recipe.getId(), 
                recipe.getUser().getName(), 
                recipe.getName(), 
                recipe.getCategory().getName(), 
                recipe.getCreatedAt(), 
                recipe.getPreparationMethod(), 
                recipe.getPortions(), 
                recipe.getDescription(), 
                recipe.getIsPublished(), 
                recipe.getIsRated()
            )).collect(Collectors.toList());
    }

    public RecipeResponseDTO getRecipeById(Long id) {
        Recipe recipe = verifyIfRecipeExists(id);

        return new RecipeResponseDTO(recipe.getId(), 
            recipe.getUser().getName(), 
            recipe.getName(), 
            recipe.getCategory().getName(), 
            recipe.getCreatedAt(), 
            recipe.getPreparationMethod(), 
            recipe.getPortions(), 
            recipe.getDescription(), 
            recipe.getIsPublished(), 
            recipe.getIsRated());
    }

    public RecipeResponseDTO updateRecipeById(Long id, RecipeRequestDTO request) {
        Recipe recipe = verifyIfRecipeExists(id);

        recipe.setName(request.name());
        Category category = findCategoryByName(request.categoryName());
        recipe.setCategory(category);
        recipe.setPreparationMethod(request.preparationMethod());
        recipe.setPortions(request.portions());
        recipe.setDescription(request.description());


        Recipe updatedRecipe = recipeRepository.save(recipe);

        return new RecipeResponseDTO(
            updatedRecipe.getId(),
            updatedRecipe.getUser().getName(),
            updatedRecipe.getName(),
            updatedRecipe.getCategory().getName(),
            updatedRecipe.getCreatedAt(),
            updatedRecipe.getPreparationMethod(),
            updatedRecipe.getPortions(),
            updatedRecipe.getDescription(),
            updatedRecipe.getIsPublished(),
            updatedRecipe.getIsRated()
        );
    }

    public void deleteRecipeById(Long id) {
        if (recipeRepository.existsById(id)) {
            recipeRepository.deleteById(id);
        } else {
            throw new RuntimeException("Recipe not found");
        }
    }

    private RecipeVerificationResult checkIfSameRecipeExistsForUser(Long userId, String name) {
        //Move to other method
        User user = findUserById(userId);
        boolean exists = recipeRepository.existsByUserAndName(user, name);

        if(exists) {
            throw new RecipeAlreadyExistsException(
                String.format("The same recipe cannot be created by the same chef. Name: %s Chef: %s", name, user.getName())
            );
        } 
        return new RecipeVerificationResult(exists, user);
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private Category findCategoryByName(String nameCategory) {
        return categoryRepository.findByName(nameCategory)
            .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    private Recipe verifyIfRecipeExists(Long id) {
        return recipeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Recipe not found"));
    }
}
