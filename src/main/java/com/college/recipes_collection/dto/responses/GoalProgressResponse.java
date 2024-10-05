package com.college.recipes_collection.dto.responses;

public record GoalProgressResponse(
    Long userId,
    int target,
    int progress
) {
    
}
