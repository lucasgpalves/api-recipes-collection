package com.college.recipes_collection.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.recipes_collection.dto.requests.RestaurantRequestDTO;
import com.college.recipes_collection.dto.responses.RestaurantResponseDTO;
import com.college.recipes_collection.models.Category;
import com.college.recipes_collection.models.Restaurant;
import com.college.recipes_collection.repositories.CategoryRepository;
import com.college.recipes_collection.repositories.RestaurantRepository;

@Service
public class RestaurantService {
    
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired CategoryRepository categoryRepository;

    public void createRestaurant(RestaurantRequestDTO request) {
        Restaurant restaurant = new Restaurant();

        saveRestaurant(restaurant, request);
    }

    public List<RestaurantResponseDTO> getAllRestaurants() {
        return restaurantRepository.findAll().stream()
            .map(restaurant -> new RestaurantResponseDTO(
                restaurant.getName(), 
                restaurant.getCity(),
                restaurant.getAddress(),
                restaurant.getRestaurantType(),
                restaurant.getCnpj(),
                restaurant.getCategory().getName()
            )).collect(Collectors.toList());
    }

    public RestaurantResponseDTO getRestaurantById(Long id) {
        Restaurant restaurant = findById(id);

        return new RestaurantResponseDTO(
            restaurant.getName(), 
            restaurant.getCity(),
            restaurant.getAddress(),
            restaurant.getRestaurantType(),
            restaurant.getCnpj(),
            restaurant.getCategory().getName()
            );
    }

    public void updateRestaurantById(RestaurantRequestDTO request, Long id) {
        Restaurant updatedRestaurant = findById(id);

        saveRestaurant(updatedRestaurant, request);
    }

    public void deleteRestaurantById(Long id) {
        if(restaurantRepository.existsById(id)) {
            restaurantRepository.deleteById(id);
        } else {
            throw new RuntimeException("Restaurant not found");
        }
    }

    private void saveRestaurant(Restaurant restaurant,RestaurantRequestDTO request) {
        restaurant.setName(request.name());
        restaurant.setCity(request.city());
        restaurant.setAddress(request.address());

        Category category = categoryRepository.findById(request.categoryId())
                            .orElseThrow(() -> new RuntimeException("Category not found"));
        restaurant.setCategory(category);

        restaurant.setCnpj(request.cnpj());
        restaurant.setRestaurantType(request.restaurantType());

        restaurantRepository.save(restaurant);
    }

    private Restaurant findById(Long id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new RuntimeException("Restaurant not found"));
    }
}
