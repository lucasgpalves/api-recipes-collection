package com.college.recipes_collection.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequestDTO(
    @NotBlank
    String cpf,

    @NotBlank
    String name,

    @NotBlank
    String roleName,

    @NotNull
    Double salary,

    String fantasyName
) {
    public UserRequestDTO(
        @NotNull @NotBlank String cpf,
        @NotNull @NotBlank String name,
        @NotNull @NotBlank String roleName,
        @NotNull Double salary,
        String fantasyName
    ) {
        this.cpf = cpf;
        this.name = name;
        this.roleName = roleName.toUpperCase();
        this.salary = salary;
        this.fantasyName = (fantasyName == null || fantasyName.isBlank()) ? "" : fantasyName;
    }
    
}
