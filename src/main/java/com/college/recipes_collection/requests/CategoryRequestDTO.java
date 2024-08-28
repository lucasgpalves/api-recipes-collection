package com.college.recipes_collection.requests;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDTO(
    @NotBlank 
    String name
) {
    
}
