package com.college.recipes_collection.dto;

import jakarta.validation.constraints.NotBlank;

public record RoleDTO(
    @NotBlank
    String name
) {
    
}
