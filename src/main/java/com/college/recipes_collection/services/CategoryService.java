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
        categoryRepository.save(category);
    }

    public CategoryResponseDTO getCategoryById(int id) {
        Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Category not found"));

        return new CategoryResponseDTO(
            category.getId(), 
            category.getName()
        );
    }

    public List<CategoryResponseDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
            .map(category -> new CategoryResponseDTO(
                category.getId(), 
                category.getName()
            ))
            .collect(Collectors.toList());
    }

    public CategoryResponseDTO updateCategory(int id, CategoryRequestDTO request) {
        Category updatedCategory = categoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Category not found"));

        updatedCategory.setName(request.name());
        Category savedCategory = categoryRepository.save(updatedCategory);

        return new CategoryResponseDTO(
            savedCategory.getId(), 
            savedCategory.getName()
        );
    }

    public void deleteCategory(int id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new RuntimeException("Category not found");
        }
    }
}
