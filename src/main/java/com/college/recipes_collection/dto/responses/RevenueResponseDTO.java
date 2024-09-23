package com.college.recipes_collection.dto.responses;

public record RevenueResponseDTO(
    Double rating,
    String description,
    Long userId,
    Long recipeId
) {
    
}
