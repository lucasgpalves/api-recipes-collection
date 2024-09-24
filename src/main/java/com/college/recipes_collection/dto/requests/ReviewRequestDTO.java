package com.college.recipes_collection.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReviewRequestDTO(
    @NotNull Double rating,
    @NotBlank String description,
    @NotNull Long userId,
    @NotNull Long recipeId
) {
    
}
