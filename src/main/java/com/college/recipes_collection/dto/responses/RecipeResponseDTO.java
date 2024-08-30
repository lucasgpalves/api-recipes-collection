package com.college.recipes_collection.dto.responses;

import java.time.LocalDateTime;

public record RecipeResponseDTO(
    Long id,
    String userName,
    String name,
    String categoryName,
    LocalDateTime createdAt,
    String preparationMethod,
    Double portions,
    String description,
    Boolean isPublished,
    Boolean isRated
) {
    
}
