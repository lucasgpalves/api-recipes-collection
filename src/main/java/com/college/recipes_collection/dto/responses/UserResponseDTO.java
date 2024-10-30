package com.college.recipes_collection.dto.responses;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record UserResponseDTO(
    String cpf,
    String name,
    String roleName,
    Double salary,
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    LocalDateTime ingressedAt,
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    LocalDateTime terminatedAt,
    String fantasyName
) {
    
}
