package com.college.recipes_collection.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.recipes_collection.dto.responses.CategoryResponseDTO;
import com.college.recipes_collection.models.Category;
import com.college.recipes_collection.repositories.CategoryRepository;
import com.college.recipes_collection.dto.requests.CategoryRequestDTO;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;

    public void createCategory(CategoryRequestDTO request) {
        Category category = new Category();
        category.setName(request.name());
        category.setDescription(request.description());
        categoryRepository.save(category);
    }

    public CategoryResponseDTO getCategoryById(int id) {
        Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Category not found"));

        return new CategoryResponseDTO(
            category.getId(), 
            category.getName(),
            category.getDescription()
        );
    }

    public List<CategoryResponseDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
            .map(category -> new CategoryResponseDTO(
                category.getId(), 
                category.getName(),
                category.getDescription()
            ))
            .collect(Collectors.toList());
    }

    public void updateCategory(int id, CategoryRequestDTO request) {
        Category updatedCategory = categoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Category not found"));

        updatedCategory.setName(request.name());
        updatedCategory.setDescription(request.description());
        categoryRepository.save(updatedCategory);
    }

    public void deleteCategory(int id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new RuntimeException("Category not found");
        }
    }
}
