package com.college.recipes_collection.dto.responses;

import java.time.LocalDate;

public record GoalResponseDTO(
    Long id,
    String title,
    int amount,
    String description,
    LocalDate startsAt,
    RoleResponseDTO role
) {
    
}
