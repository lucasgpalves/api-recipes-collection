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

import com.college.recipes_collection.dto.responses.IngredientResponseDTO;
import com.college.recipes_collection.dto.requests.IngredientRequestDTO;
import com.college.recipes_collection.services.IngredientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {
    
    @Autowired
    private IngredientService ingredientService;

    @PostMapping
    public ResponseEntity<Void> createIngredient(@Valid @RequestBody IngredientRequestDTO request) {
        ingredientService.createIngredient(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientResponseDTO> getIngredientById(@PathVariable int id) {
        IngredientResponseDTO ingredient = ingredientService.getIngredientById(id);
        return ResponseEntity.ok(ingredient);
    }

    @GetMapping
    public ResponseEntity<List<IngredientResponseDTO>> getAllIngredients() {
        List<IngredientResponseDTO> ingredients = ingredientService.getAllIngredients();
        return ResponseEntity.ok(ingredients);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngredientResponseDTO> updateIngredient(@PathVariable int id,@Valid @RequestBody IngredientRequestDTO request) {
        IngredientResponseDTO updatedIngredient = ingredientService.updateIngredient(id, request);
        return ResponseEntity.ok(updatedIngredient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable int id) {
        ingredientService.deleteIngredient(id);
        return ResponseEntity.noContent().build();
    }
}
