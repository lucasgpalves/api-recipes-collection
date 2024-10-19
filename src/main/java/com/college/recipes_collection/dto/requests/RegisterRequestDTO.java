package com.college.recipes_collection.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterRequestDTO(
    
    @NotBlank
    String username,

    @NotBlank
    String password,

    @NotNull
    Long userId
) {
    
}
