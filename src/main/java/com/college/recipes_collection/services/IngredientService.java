package com.college.recipes_collection.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.recipes_collection.dto.responses.IngredientResponseDTO;
import com.college.recipes_collection.models.Ingredient;
import com.college.recipes_collection.repositories.IngredientRepository;
import com.college.recipes_collection.dto.requests.IngredientRequestDTO;

@Service
public class IngredientService {
    
    @Autowired
    private IngredientRepository ingredientRepository;

    public void createIngredient(IngredientRequestDTO request) {
        Ingredient ingredient = new Ingredient();
        
        ingredient.setName(request.name());
        ingredient.setDescription(request.description());

        ingredientRepository.save(ingredient);
    }

    public IngredientResponseDTO getIngredientById(int id) {
        Ingredient ingredient = ingredientRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Ingredient not found"));

        return new IngredientResponseDTO(
            ingredient.getId(), 
            ingredient.getName(), 
            ingredient.getDescription()
        );
    }

    public List<IngredientResponseDTO> getAllIngredients() {
        return ingredientRepository.findAll().stream()
            .map(ingredient -> new IngredientResponseDTO(
                ingredient.getId(), 
                ingredient.getName(), 
                ingredient.getDescription()
            ))
            .collect(Collectors.toList());
    }

    public void updateIngredient(int id, IngredientRequestDTO request) {
        Ingredient updatedIngredient = ingredientRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Ingredient not found"));

        updatedIngredient.setName(request.name());
        updatedIngredient.setDescription(request.description());

        ingredientRepository.save(updatedIngredient);
    }

    public void deleteIngredient(int id) {
        if (ingredientRepository.existsById(id)) {
            ingredientRepository.deleteById(id);
        } else {
            throw new RuntimeException("Ingredient not found");
        }
    }
}
