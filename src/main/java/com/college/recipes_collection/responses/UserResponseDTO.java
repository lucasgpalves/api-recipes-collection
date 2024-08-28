package com.college.recipes_collection.responses;

public record UserResponseDTO(
    String cpf,
    String name,
    String roleName,
    Double salary,
    String fantasyName
) {
    
}
