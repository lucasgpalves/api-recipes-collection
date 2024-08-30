package com.college.recipes_collection.dto.responses;

public record RecipeSummariesDTO(
    Long id,
    String userName,
    String name,
    String categoryName
) {
    
}
