package com.college.recipes_collection.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RestaurantRequestDTO(
    @NotBlank
    String name,

    @NotBlank
    String city,

    @NotBlank
    String address,

    @NotBlank
    String restaurantType,

    String cnpj,

    @NotNull
    int categoryId
) {

}
