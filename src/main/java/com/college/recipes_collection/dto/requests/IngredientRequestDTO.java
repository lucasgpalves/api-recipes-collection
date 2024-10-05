package com.college.recipes_collection.dto.requests;

import jakarta.validation.constraints.NotBlank;

public record IngredientRequestDTO(
    @NotBlank
    String name,

    String description
) {
    public IngredientRequestDTO{
        //Mudar o valor atribuido para "" e não null
        description = (description == null || description.isBlank()) ? "" : description;
    }
}
