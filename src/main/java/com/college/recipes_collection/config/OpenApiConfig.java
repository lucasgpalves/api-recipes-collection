package com.college.recipes_collection.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Recipes Collection",
        version = "v1",
        description = "Documentation of recipes_collection"
    )
)
public class OpenApiConfig {
    // Você pode adicionar outras configurações aqui, se necessário.
}

