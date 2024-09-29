package com.college.recipes_collection.dto.responses;

import java.util.List;

import com.college.recipes_collection.dto.RecipeNameDTO;

public record BookResponseDTO(
    String title,
    String editorName,
    String isbn,
    String description,
    List<RecipeNameDTO> recipesName
) {
    
}
