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

import com.college.recipes_collection.dto.requests.RevenueRequestDTO;
import com.college.recipes_collection.dto.responses.RevenueResponseDTO;
import com.college.recipes_collection.services.RevenueService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/revenues")
public class RevenueController {
    
    @Autowired
    private RevenueService revenueService;

    @PostMapping
    public ResponseEntity<Void> createRevenue(@Valid @RequestBody RevenueRequestDTO request) {
        revenueService.createRevenue(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping 
    public ResponseEntity<List<RevenueResponseDTO>> getAllRevenues() {
        List<RevenueResponseDTO> revenues = revenueService.getAllRevenues();
        return ResponseEntity.ok(revenues);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RevenueResponseDTO> getRevenueById(@PathVariable Long id) {
        RevenueResponseDTO revenue = revenueService.getRevenueById(id);
        return ResponseEntity.ok(revenue);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRevenueById(@PathVariable Long id, @Valid @RequestBody RevenueRequestDTO request) {
        revenueService.updateRevenueById(id ,request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRevenueById(@PathVariable Long id) {
        revenueService.deleteRevenueById(id);
        return ResponseEntity.noContent().build();
    }
}
