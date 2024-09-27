package com.college.recipes_collection.dto.requests;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record BookRequestDTO(
    @NotBlank
    String title,

    @NotBlank
    String isbn,

    @NotEmpty
    List<Long> recipesId,

    @NotNull
    Long userId
) {
    
}