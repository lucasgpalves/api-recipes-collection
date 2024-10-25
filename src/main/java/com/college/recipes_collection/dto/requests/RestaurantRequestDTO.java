package com.college.recipes_collection.dto.requests;

import jakarta.validation.constraints.NotBlank;

public record RestaurantRequestDTO(
    @NotBlank
    String name
) {

}
