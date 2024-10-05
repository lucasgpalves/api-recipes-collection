package com.college.recipes_collection.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.college.recipes_collection.dto.requests.GoalRequestDTO;
import com.college.recipes_collection.dto.responses.GoalProgressResponse;
import com.college.recipes_collection.dto.responses.GoalResponseDTO;
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

    @GetMapping
    public ResponseEntity<List<GoalResponseDTO>> gelAllGoals() {
        List<GoalResponseDTO> goals = goalService.getAllGoals();
        return ResponseEntity.ok(goals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GoalResponseDTO> getGoalById(@PathVariable Long id) {
        GoalResponseDTO goal = goalService.getGoalById(id);
        return ResponseEntity.ok(goal);
    }

    @GetMapping("/{goalId}/progress")
    public ResponseEntity<GoalProgressResponse> getGoalProgress(
        @PathVariable Long goalId,
        @RequestParam Long userId,
        @RequestParam(required = false) Integer month,
        @RequestParam(required = false) Integer year
    ) {
        int currentMonth = (month == null) ? LocalDateTime.now().getMonthValue() : month;
        int currentYear = (year == null) ? LocalDateTime.now().getYear() : year;
        GoalProgressResponse goal = goalService.getGoalProgress(goalId, userId, currentMonth, currentYear);

        return ResponseEntity.ok(goal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGoalById(@PathVariable Long id, @RequestBody GoalRequestDTO request) {
        goalService.updateGoalById(id, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGoalById(@PathVariable Long id) {
        goalService.deleteGoalById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
