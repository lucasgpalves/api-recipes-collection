package com.college.recipes_collection.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.recipes_collection.dto.requests.RestaurantRequestDTO;
import com.college.recipes_collection.dto.responses.RestaurantResponseDTO;
import com.college.recipes_collection.models.Restaurant;
import com.college.recipes_collection.repositories.RestaurantRepository;

@Service
public class RestaurantService {
    
    @Autowired
    private RestaurantRepository restaurantRepository;

    public void createRestaurant(RestaurantRequestDTO request) {
        Restaurant restaurant = new Restaurant();

        restaurant.setName(request.name());

        restaurantRepository.save(restaurant);
    }

    public List<RestaurantResponseDTO> getAllRestaurants() {
        return restaurantRepository.findAll().stream()
            .map(restaurant -> new RestaurantResponseDTO(
                restaurant.getName()
            )).collect(Collectors.toList());
    }

    public RestaurantResponseDTO getRestaurantById(Long id) {
        Restaurant restaurant = findById(id);

        return new RestaurantResponseDTO(restaurant.getName());
    }

    public void updateRestaurantById(RestaurantRequestDTO request, Long id) {
        Restaurant restaurant = findById(id);

        restaurant.setName(request.name());

        restaurantRepository.save(restaurant);
    }

    public void deleteRestaurantById(Long id) {
        if(restaurantRepository.existsById(id)) {
            restaurantRepository.deleteById(id);
        } else {
            throw new RuntimeException("Restaurant not found");
        }
    }

    private Restaurant findById(Long id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new RuntimeException("Restaurant not found"));
    }
}
