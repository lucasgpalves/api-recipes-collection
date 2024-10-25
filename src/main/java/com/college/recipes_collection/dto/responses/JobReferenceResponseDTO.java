package com.college.recipes_collection.dto.responses;

import java.time.LocalDate;

public record JobReferenceResponseDTO(
    String restaurantName,
    LocalDate startsAt,
    LocalDate endsAt,
    String contactNumber,
    UserResponseDTO userId
) {
    
}
