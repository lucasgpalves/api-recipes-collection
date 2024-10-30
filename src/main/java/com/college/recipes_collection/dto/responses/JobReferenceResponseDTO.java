package com.college.recipes_collection.dto.responses;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public record JobReferenceResponseDTO(
    String restaurantName,
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate startsAt,
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate endsAt,
    String contactNumber,
    UserResponseDTO user
) {
    
}
