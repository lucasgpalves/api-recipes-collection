package com.college.recipes_collection.dto.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequestDTO(
    @NotBlank
    String cpf,

    @NotBlank
    String name,

    @NotBlank
    String roleName,

    @NotNull
    @Min(value = 0, message = "Salary must be greater than or equal to 0")
    Double salary,

    String fantasyName,

    String restaurantName
) {
    public UserRequestDTO {
        roleName = roleName.toUpperCase();
        fantasyName = (fantasyName == null || fantasyName.isBlank()) ? "" : fantasyName;
        restaurantName = (restaurantName == null || restaurantName.isBlank()) ? null : restaurantName;
    }
    
}
