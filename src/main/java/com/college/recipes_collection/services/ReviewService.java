package com.college.recipes_collection.services;

import java.util.stream.Collectors;
import java.util.List;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.college.recipes_collection.dto.requests.ReviewRequestDTO;
import com.college.recipes_collection.dto.responses.ReviewResponseDTO;
import com.college.recipes_collection.events.models.ReviewCreatedEvent;
import com.college.recipes_collection.models.Recipe;
import com.college.recipes_collection.models.Review;
import com.college.recipes_collection.models.User;
import com.college.recipes_collection.repositories.RecipeRepository;
import com.college.recipes_collection.repositories.ReviewRepository;
import com.college.recipes_collection.repositories.UserRepository;

@Service
public class ReviewService {
    
    @Autowired
    private ReviewRepository revenueRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void createRevenue(ReviewRequestDTO request) {
        Review revenue = new Review();
        saveRevenue(revenue, request);
        eventPublisher.publishEvent(new ReviewCreatedEvent(request.recipeId()));
    }

    public List<ReviewResponseDTO> getAllRevenues() {
        return revenueRepository.findAll().stream()
            .map(revenue -> new ReviewResponseDTO(
                revenue.getRating(), 
                revenue.getDescription(), 
                revenue.getUser().getId(), 
                revenue.getRecipe().getId()
            )).collect(Collectors.toList());
    }

    public ReviewResponseDTO getRevenueById(Long id) {
        Review revenue = revenueRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Revenue not found"));
        return new ReviewResponseDTO(
            revenue.getRating(), 
            revenue.getDescription(), 
            revenue.getUser().getId(), 
            revenue.getRecipe().getId());
    }

    public void updateRevenueById(Long id, ReviewRequestDTO request) {
        Review revenue = revenueRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Revenue not found"));
        saveRevenue(revenue, request);
    }

    public void deleteRevenueById(Long id) {
        if (revenueRepository.existsById(id)) {
            revenueRepository.deleteById(id);
        } else {
            throw new RuntimeException("Revenue not found");
        }
    }

    private void saveRevenue(Review revenue, ReviewRequestDTO request) {
        revenue.setRating(request.rating());
        revenue.setDescription(request.description());
        revenue.setUser(findUser(request.userId()));
        revenue.setRecipe(findRecipe(request.recipeId()));
        revenue.setCreatedAt(LocalDateTime.now());

        revenueRepository.save(revenue);
    }

    private User findUser(Long id) {  
        return userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private Recipe findRecipe(Long id) {
        return recipeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Recipe not found"));
    }

}
