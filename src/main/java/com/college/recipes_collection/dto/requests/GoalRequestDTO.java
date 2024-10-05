package com.college.recipes_collection.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GoalRequestDTO(
    @NotBlank
    String title,

    @NotNull
    int amount,

    String description,

    @NotBlank
    String roleName
) {
    public GoalRequestDTO {
        roleName = roleName.toUpperCase();
        description = (description == null || description.isBlank()) ? "" : description;
    }
}
