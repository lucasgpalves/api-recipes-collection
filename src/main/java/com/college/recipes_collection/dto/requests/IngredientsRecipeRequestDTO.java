package com.college.recipes_collection.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record IngredientsRecipeRequestDTO(
    @NotNull Double amount,
    @NotBlank String ingredientName,
    @NotBlank String measurementName
) {
    
}
