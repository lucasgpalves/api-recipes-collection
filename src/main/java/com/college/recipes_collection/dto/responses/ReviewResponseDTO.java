package com.college.recipes_collection.dto.responses;

public record ReviewResponseDTO(
    Double rating,
    String description,
    Long userId,
    Long recipeId
) {
    
}
