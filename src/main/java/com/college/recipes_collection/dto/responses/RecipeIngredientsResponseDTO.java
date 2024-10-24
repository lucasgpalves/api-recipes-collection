package com.college.recipes_collection.dto.responses;
public record RecipeIngredientsResponseDTO(
    Double amount,
    String ingredientName,
    String measurementName
) {
    
}
