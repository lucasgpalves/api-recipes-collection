package com.college.recipes_collection.dto.requests;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserReferencesRequestDTO(    
    @NotBlank
    String restaurantName,
    @NotNull
    LocalDate startsAt,
    @NotNull
    LocalDate endsAt,
    @NotBlank
    String contactNumber,
    @NotNull
    Long userId
) {
}
