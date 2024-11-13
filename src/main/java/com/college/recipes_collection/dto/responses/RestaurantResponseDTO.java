package com.college.recipes_collection.dto.responses;

public record RestaurantResponseDTO(
    String name, 
    String city,
    String address,
    String restaurantType,
    String cnpj,
    String categoryName
) {
    
}
