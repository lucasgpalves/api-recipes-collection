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

import com.college.recipes_collection.dto.responses.RecipeResponseDTO;
import com.college.recipes_collection.dto.requests.RecipeRequestDTO;
import com.college.recipes_collection.services.RecipeService;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    
    @Autowired
    private RecipeService recipeService;

    @PostMapping
    public ResponseEntity<Void> createRecipe(@RequestBody RecipeRequestDTO request) {
        recipeService.createRecipe(request);
        return ResponseEntity.ok().build();
    } 

    @GetMapping("/{id}")
    public ResponseEntity<RecipeResponseDTO> getRecipeById(@PathVariable Long id) {
        RecipeResponseDTO recipe = recipeService.getRecipeById(id);
        return ResponseEntity.ok(recipe);
    }

    @GetMapping
    public ResponseEntity<List<RecipeResponseDTO>> getAllRecipes() {
        List<RecipeResponseDTO> recipes = recipeService.getAllRecipes();
        return ResponseEntity.ok(recipes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecipeResponseDTO> updateRecipeById(@PathVariable Long id, @RequestBody RecipeRequestDTO request) {
        RecipeResponseDTO recipe = recipeService.updateRecipeById(id, request);
        return ResponseEntity.ok(recipe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipeById(@PathVariable Long id) {
        recipeService.deleteRecipeById(id);
        return ResponseEntity.noContent().build();
    }
}
