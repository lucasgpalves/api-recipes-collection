package com.college.recipes_collection.services;

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.recipes_collection.dto.RecipeVerificationResult;
import com.college.recipes_collection.dto.responses.IngredientsRecipeResponseDTO;
import com.college.recipes_collection.dto.responses.RecipeResponseDTO;
import com.college.recipes_collection.dto.responses.RecipeSummariesDTO;
import com.college.recipes_collection.exceptions.RecipeAlreadyExistsException;
import com.college.recipes_collection.models.Category;
import com.college.recipes_collection.models.Ingredient;
import com.college.recipes_collection.models.IngredientsRecipe;
import com.college.recipes_collection.models.Measurement;
import com.college.recipes_collection.models.Recipe;
import com.college.recipes_collection.models.User;
import com.college.recipes_collection.repositories.CategoryRepository;
import com.college.recipes_collection.repositories.IngredientRepository;
import com.college.recipes_collection.repositories.IngredientsRecipeRepository;
import com.college.recipes_collection.repositories.MeasurementRepository;
import com.college.recipes_collection.repositories.RecipeRepository;
import com.college.recipes_collection.repositories.UserRepository;
import com.college.recipes_collection.dto.requests.IngredientsRecipeRequestDTO;
import com.college.recipes_collection.dto.requests.RecipeRequestDTO;

@Service
public class RecipeService {
    
    @Autowired
    private RecipeRepository recipeRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private IngredientRepository ingredientRepository;
    
    @Autowired
    private MeasurementRepository measurementRepository;
    
    @Autowired
    private IngredientsRecipeRepository ingredientsRecipeRepository;

    public void createRecipe(RecipeRequestDTO request) {
        RecipeVerificationResult result = checkIfSameRecipeExistsForUser(request.userId(), request.name());

        Recipe recipe = new Recipe();
        recipe.setUser(result.getUser());

        Recipe createdRecipe = saveRecipe(recipe, request);
        saveIngredientForRecipe(createdRecipe, request.ingredientsRecipe());
    }       

    public List<RecipeSummariesDTO> getAllRecipes() {
        return recipeRepository.findAll().stream()
            .map(recipe -> new RecipeSummariesDTO(
                recipe.getId(), 
                recipe.getUser().getName(), 
                recipe.getName(), 
                recipe.getCategory().getName()
            )).collect(Collectors.toList());
    }

    public RecipeResponseDTO getRecipeById(Long id) {
        Recipe recipe = verifyIfRecipeExists(id);
        return mapToRecipeResponseDTO(recipe);
    }

    public void updateRecipeById(Long id, RecipeRequestDTO request) {
        Recipe recipe = verifyIfRecipeExists(id);
        Recipe updatedRecipe = saveRecipe(recipe, request);
        saveIngredientForRecipe(updatedRecipe, request.ingredientsRecipe());
    }

    public void updateIsRatingStatus(Long recipeId, boolean isRated) {
        Recipe recipe = verifyIfRecipeExists(recipeId);
        recipe.setIsRated(isRated);
        recipeRepository.save(recipe);
    }

    public void updateIsPublishedStatus(Long recipeId, boolean isPublished) {
        Recipe recipe = verifyIfRecipeExists(recipeId);
        recipe.setIsPublished(isPublished);
        recipeRepository.save(recipe);
    }

    //Código utilizado para criar e atualizar receitas
    private Recipe saveRecipe(Recipe recipe, RecipeRequestDTO request) {
        recipe.setName(request.name());
        recipe.setCategory(findCategoryByName(request.categoryName()));
        recipe.setPreparationMethod(request.preparationMethod());
        recipe.setPortions(request.portions());
        recipe.setDescription(request.description());
        recipe.setPreparationTime(request.preparationTime());

        //Será executado apenas durante a criação
        if (recipe.getId() == null) {
            recipe.setCreatedAt(LocalDateTime.now());
            recipe.setIsPublished(false);
            recipe.setIsRated(false);
        }

        return recipeRepository.save(recipe);
    }
    
    private void saveIngredientForRecipe(Recipe recipe, List<IngredientsRecipeRequestDTO> ingredientsRequestDto) {
        for (IngredientsRecipeRequestDTO ingredientRequest : ingredientsRequestDto) {
            IngredientsRecipe ingredientsRecipe = new IngredientsRecipe();
            ingredientsRecipe.setRecipe(recipe);

            ingredientsRecipe.setAmount(ingredientRequest.amount());

            Ingredient ingredient = findIngredientByName(ingredientRequest.ingredientName());
            ingredientsRecipe.setIngredient(ingredient);

            Measurement measurement = findMeasurementByName(ingredientRequest.measurementName());
            ingredientsRecipe.setMeasurement(measurement);

            ingredientsRecipeRepository.save(ingredientsRecipe);            
        }
    }

    private RecipeResponseDTO mapToRecipeResponseDTO(Recipe recipe) {
        List<IngredientsRecipeResponseDTO> ingredientsDtos = recipe.getIngredients().stream()
            .map(this::mapToIngredientsRecipeResponseDto)
            .toList(); 

        return new RecipeResponseDTO(
            recipe.getId(),
            recipe.getUser().getName(),
            recipe.getName(),
            recipe.getCategory().getName(),
            recipe.getCreatedAt(), 
            recipe.getPreparationMethod(),
            recipe.getPortions(),
            ingredientsDtos,
            recipe.getDescription(),
            recipe.getIsPublished(),
            recipe.getIsRated()
        );
    }

    private IngredientsRecipeResponseDTO mapToIngredientsRecipeResponseDto(IngredientsRecipe ingredientsRecipe) {
        return new IngredientsRecipeResponseDTO(
            ingredientsRecipe.getAmount(), 
            ingredientsRecipe.getIngredient().getName(), 
            ingredientsRecipe.getMeasurement().getName()
        );
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

    private Ingredient findIngredientByName(String nameIngredient) {
        return ingredientRepository.findByName(nameIngredient)
            .orElseThrow(() -> new RuntimeException("Ingredient not found"));
    }

    private Measurement findMeasurementByName(String nameMeasurement) {
        return measurementRepository.findByName(nameMeasurement)
        .orElseThrow(() -> new RuntimeException("Measurement not found"));
    }

    private Category findCategoryByName(String nameCategory) {
        return categoryRepository.findByName(nameCategory)
            .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private Recipe verifyIfRecipeExists(Long id) {
        return recipeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Recipe not found"));
    }
}