package com.college.recipes_collection.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.college.recipes_collection.dto.requests.GoalRequestDTO;
import com.college.recipes_collection.services.GoalService;

@RestController
@RequestMapping("/goals")
public class GoalController {
    
    @Autowired
    private GoalService goalService;

    @PostMapping
    public ResponseEntity<Void> createGoal(@RequestBody GoalRequestDTO request) {
        goalService.createGoal(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
}
