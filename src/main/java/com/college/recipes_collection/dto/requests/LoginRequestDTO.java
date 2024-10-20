package com.college.recipes_collection.dto.requests;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
    @NotBlank
    String username,

    @NotBlank
    String password
) {
    
}
