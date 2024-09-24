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
    private ReviewService revenueService;

    @PostMapping
    public ResponseEntity<Void> createRevenue(@Valid @RequestBody ReviewRequestDTO request) {
        revenueService.createRevenue(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping 
    public ResponseEntity<List<ReviewResponseDTO>> getAllRevenues() {
        List<ReviewResponseDTO> revenues = revenueService.getAllRevenues();
        return ResponseEntity.ok(revenues);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponseDTO> getRevenueById(@PathVariable Long id) {
        ReviewResponseDTO revenue = revenueService.getRevenueById(id);
        return ResponseEntity.ok(revenue);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRevenueById(@PathVariable Long id, @Valid @RequestBody ReviewRequestDTO request) {
        revenueService.updateRevenueById(id ,request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRevenueById(@PathVariable Long id) {
        revenueService.deleteRevenueById(id);
        return ResponseEntity.noContent().build();
    }
}
