package com.college.recipes_collection.dto.responses;

import java.time.LocalDateTime;
import java.util.List;

public record RecipeResponseDTO(
    Long id,
    String userName,
    String name,
    String categoryName,
    LocalDateTime createdAt,
    String preparationMethod,
    Double portions,
    List<RecipeIngredientsResponseDTO> ingredients,
    String description,
    Boolean isPublished,
    Boolean isRated
) {}
