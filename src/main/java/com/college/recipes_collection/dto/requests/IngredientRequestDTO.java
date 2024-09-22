package com.college.recipes_collection.dto.requests;

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
        //Mudar o valor atribuido para "" e não null
        this.description = (description == null || description.isBlank()) ? null : description;
    }
}
