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

    @NotNull
    int preparationTime,

    @NotNull
    List<RecipeIngredientsRequestDTO> ingredientsRecipe,

    String description
) {
    public RecipeRequestDTO {
        description = (description == null || description.isBlank()) ? "" : description;
    }
}
