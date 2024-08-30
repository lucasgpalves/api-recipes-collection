package com.college.recipes_collection.dto.requests;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RecipeRequestDTO(
    @NotNull 
    Long userId,

    @NotBlank
    String name,

    @NotBlank
    String categoryName,

    @NotBlank
    String preparationMethod,

    @NotNull
    Double portions,

    List<IngredientsRecipeRequestDTO> ingredientsRecipe,

    String description
) {
    public RecipeRequestDTO(
        @NotNull Long userId,
        @NotBlank String name,
        @NotBlank String categoryName,
        @NotBlank String preparationMethod,
        @NotNull Double portions,
        List<IngredientsRecipeRequestDTO> ingredientsRecipe,
        String description
    ) {
        this.userId = userId;
        this.name = name;
        this.categoryName = categoryName;
        this.preparationMethod = preparationMethod;
        this.portions = portions;
        this.ingredientsRecipe = ingredientsRecipe;
        this.description = (description == null || description.isBlank()) ? "" : description;
    }
}
