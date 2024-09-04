package com.college.recipes_collection.services;

import java.util.List;
import java.util.stream.Collectors;

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
    
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final IngredientRepository ingredientRepository;
    private final MeasurementRepository measurementRepository;
    private final IngredientsRecipeRepository ingredientsRecipeRepository;

    public RecipeService(RecipeRepository recipeRepository, 
        UserRepository userRepository, 
        CategoryRepository categoryRepository, 
        IngredientRepository ingredientRepository, 
        MeasurementRepository measurementRepository,
        IngredientsRecipeRepository ingredientsRecipeRepository
    ) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.ingredientRepository = ingredientRepository;
        this.measurementRepository = measurementRepository;
        this.ingredientsRecipeRepository = ingredientsRecipeRepository;
    }

    public void createRecipe(RecipeRequestDTO request) {
        RecipeVerificationResult result = checkIfSameRecipeExistsForUser(request.userId(), request.name());

        Recipe recipe = new Recipe();
        recipe.setUser(result.getUser());

        Recipe createdRecipe = saveRecipe(recipe, request);
        saveIngredientForRecipe(createdRecipe, request.ingredientsRecipe());
    }       

    private void saveIngredientForRecipe(Recipe recipe, List<IngredientsRecipeRequestDTO> ingredientsRequestDto) {
        for (IngredientsRecipeRequestDTO ingredientRequest : ingredientsRequestDto) {
            IngredientsRecipe ingredientsRecipe = new IngredientsRecipe();
            ingredientsRecipe.setAmount(ingredientRequest.amount());

            Ingredient ingredient = findIngredientByName(ingredientRequest.ingredientName());
            ingredientsRecipe.setIngredient(ingredient);

            Measurement measurement = findMeasurementByName(ingredientRequest.measurementName());
            ingredientsRecipe.setMeasurement(measurement);

            ingredientsRecipeRepository.save(ingredientsRecipe);            
        }
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

    //Atualizado o tipo de retorno
    public void updateRecipeById(Long id, RecipeRequestDTO request) {
        Recipe recipe = verifyIfRecipeExists(id);
        Recipe updatedRecipe = saveRecipe(recipe, request);
        saveIngredientForRecipe(updatedRecipe, request.ingredientsRecipe());
    }

    public void deleteRecipeById(Long id) {
        if (recipeRepository.existsById(id)) {
            recipeRepository.deleteById(id);
        } else {
            throw new RuntimeException("Recipe not found");
        }
    }

    private Recipe saveRecipe(Recipe recipe, RecipeRequestDTO request) {
        recipe.setName(request.name());
        recipe.setCategory(findCategoryByName(request.categoryName()));
        recipe.setPreparationMethod(request.preparationMethod());
        recipe.setPortions(request.portions());
        recipe.setDescription(request.description());

        if (recipe.getId() == null) {
            recipe.setIsPublished(false);
            recipe.setIsRated(false);
        }

        return recipeRepository.save(recipe);
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
