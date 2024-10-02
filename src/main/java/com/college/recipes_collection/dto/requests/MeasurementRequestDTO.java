package com.college.recipes_collection.dto.requests;

import jakarta.validation.constraints.NotBlank;

public record MeasurementRequestDTO(
    @NotBlank(message = "Name can't be blank")
    String name,
    String description
) {
    public MeasurementRequestDTO {
        name = name.toUpperCase();
        description = (description == null || description.isBlank()) ? "" : description;
    }
}
