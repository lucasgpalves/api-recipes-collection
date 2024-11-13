package com.college.recipes_collection.dto.responses;

public record RoleResponseDTO(
    String name,
    String description
) {
    public RoleResponseDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
