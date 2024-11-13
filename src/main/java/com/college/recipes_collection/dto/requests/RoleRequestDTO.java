package com.college.recipes_collection.dto.requests;

import jakarta.validation.constraints.NotBlank;

public record RoleRequestDTO(
    @NotBlank String name,
    String description
) {
    public RoleRequestDTO(
        @NotBlank String name,
        String description
    ) {
        this.name = name.toUpperCase();
        this.description = (description == null || description.isBlank()) ? null : description;
    }
}
