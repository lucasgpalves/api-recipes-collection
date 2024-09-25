package com.college.recipes_collection.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.college.recipes_collection.dto.requests.ReviewRequestDTO;
import com.college.recipes_collection.dto.responses.ReviewResponseDTO;
import com.college.recipes_collection.services.ReviewService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Void> createReview(@Valid @RequestBody ReviewRequestDTO request) {
        reviewService.createReview(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping 
    public ResponseEntity<List<ReviewResponseDTO>> getAllRevenues() {
        List<ReviewResponseDTO> revenues = reviewService.getAllReviews();
        return ResponseEntity.ok(revenues);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponseDTO> getReviewById(@PathVariable Long id) {
        ReviewResponseDTO revenue = reviewService.getReviewById(id);
        return ResponseEntity.ok(revenue);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateReviewById(@PathVariable Long id, @Valid @RequestBody ReviewRequestDTO request) {
        reviewService.updateReviewById(id ,request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReviewById(@PathVariable Long id) {
        reviewService.deleteReviewById(id);
        return ResponseEntity.noContent().build();
    }
}
