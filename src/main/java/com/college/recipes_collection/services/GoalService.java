package com.college.recipes_collection.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.recipes_collection.dto.requests.GoalRequestDTO;
import com.college.recipes_collection.dto.responses.GoalProgressResponse;
import com.college.recipes_collection.dto.responses.GoalResponseDTO;
import com.college.recipes_collection.dto.responses.RoleResponseDTO;
import com.college.recipes_collection.models.Goal;
import com.college.recipes_collection.models.Recipe;
import com.college.recipes_collection.models.Role;
import com.college.recipes_collection.repositories.GoalRepository;
import com.college.recipes_collection.repositories.RoleRepository;

@Service
public class GoalService {
    
    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RecipeService recipeService;

    public void createGoal(GoalRequestDTO request) {
        Goal goal = new Goal();
        saveGoal(goal, request);
    }

    public List<GoalResponseDTO> getAllGoals() {
        return goalRepository.findAll().stream()
            .map(goal -> new GoalResponseDTO(
                goal.getId(), 
                goal.getTitle(), 
                goal.getAmount(), 
                goal.getDescription(), 
                goal.getStartsAt(), 
                new RoleResponseDTO(
                    goal.getRole().getId(), 
                    goal.getRole().getName())
            )).collect(Collectors.toList());
    }

    public GoalResponseDTO getGoalById(Long id) {
        Goal goal = findById(id);

        return new GoalResponseDTO(
            goal.getId(), 
            goal.getTitle(), 
            goal.getAmount(), 
            goal.getDescription(), 
            goal.getStartsAt(), 
            new RoleResponseDTO(
                goal.getRole().getId(), 
                goal.getRole().getName())
        );
    }

    public void updateGoalById(Long id, GoalRequestDTO request) {
        Goal goal = findById(id);
        saveGoal(goal, request);  
    }

    public void deleteGoalById(Long id) {
        if (goalRepository.existsById(id)) {
            goalRepository.deleteById(id);
        } else {
            throw new RuntimeException("Goal not found");
        }
    }

    public GoalProgressResponse getGoalProgress(Long goalId, Long userId, int month, int year) {
        Goal goal = findById(goalId);
        int target = goal.getAmount();
        
        int completed = countRecipesByUserAndMonth(userId, month, year);

        return new GoalProgressResponse(
            userId, 
            target, 
            completed
        );
    }

    private void saveGoal(Goal goal, GoalRequestDTO request) {
        goal.setTitle(request.title());
        goal.setAmount(request.amount());
        goal.setDescription(request.description());
        goal.setRole(findByName(request.roleName()));

        if (goal.getId() == null) {
            goal.setStartsAt(LocalDate.now());
        }

        goalRepository.save(goal);
    }

    private Role findByName(String name) {
        return roleRepository.findByName(name)
        .orElseThrow(() -> new RuntimeException("Role not found"));
    }

    private Goal findById(Long id) {
        return goalRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Goal not found"));
    }

    private int countRecipesByUserAndMonth(Long id, int month, int year) {
        List<Recipe> recipes = recipeService.findRecipesByUserAndMonth(id, month, year);
        return recipes.size();
    }
}
