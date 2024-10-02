package com.college.recipes_collection.dto.responses;

import java.time.LocalDate;

import com.college.recipes_collection.models.Role;

public record GoalResponseDTO(
    Long id,
    String title,
    int amount,
    String description,
    LocalDate startsAt,
    Role role
) {
    
}
