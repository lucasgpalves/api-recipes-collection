package com.college.recipes_collection.requests;

import jakarta.validation.constraints.NotBlank;

public record RoleRequestDTO(
    @NotBlank String name
) {
    public RoleRequestDTO(
        @NotBlank String name
    ) {
        this.name = name.toUpperCase();
    }
}
