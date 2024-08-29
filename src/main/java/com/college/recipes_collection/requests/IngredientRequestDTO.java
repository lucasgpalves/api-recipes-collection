package com.college.recipes_collection.requests;

import jakarta.validation.constraints.NotBlank;

public record IngredientRequestDTO(
    @NotBlank
    String name,

    String description
) {
    public IngredientRequestDTO(
        @NotBlank String name,
        String description
    ) {
        this.name = name;
        this.description = (description == null || description.isBlank()) ? null : description;
    }
}
