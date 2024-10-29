package com.college.recipes_collection.dto.responses;

import java.time.LocalDate;

public record UserReferencesResponseDTO(
    String restaurantName,
    LocalDate startsAt,
    LocalDate endsAt,
    String contactNumber,
    Long userId
) {
    
}
