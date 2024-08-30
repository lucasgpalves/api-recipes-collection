package com.college.recipes_collection.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.college.recipes_collection.dto.requests.RecipeRequestDTO;
import com.college.recipes_collection.exceptions.RecipeAlreadyExistsException;
import com.college.recipes_collection.models.Category;
import com.college.recipes_collection.models.Recipe;
import com.college.recipes_collection.models.User;
import com.college.recipes_collection.repositories.CategoryRepository;
import com.college.recipes_collection.repositories.RecipeRepository;
import com.college.recipes_collection.repositories.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private RecipeService recipeService;

    private RecipeRequestDTO request;
    private User user;
    private Category category;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setName("John Doe");

        category = new Category();
        category.setId(1);
        category.setName("Cake");

        request = new RecipeRequestDTO(
            1L, // userId
            "Recipe Name",
            "Cake",
            "Preparation Method",
            4.0,
            "Description"
        );
    }

    @Test
    void shouldCreateRecipeSuccessfully() {
        when(userRepository.findById(request.userId())).thenReturn(Optional.of(user));
        when(recipeRepository.existsByUserAndName(user, request.name())).thenReturn(false);
        when(categoryRepository.findByName(request.categoryName())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> recipeService.createRecipe(request), "Category not found");

        verify(recipeRepository, times(0)).save(any(Recipe.class));
    }
    

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        when(userRepository.findById(request.userId())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> recipeService.createRecipe(request));

        verify(recipeRepository, never()).save(any(Recipe.class));
    }

    @Test
    void shouldThrowExceptionWhenRecipeAlreadyExists() {
        when(userRepository.findById(request.userId())).thenReturn(Optional.of(user));
        when(recipeRepository.existsByUserAndName(user, request.name())).thenReturn(true);

        assertThrows(RecipeAlreadyExistsException.class, () -> recipeService.createRecipe(request));

        verify(recipeRepository, never()).save(any(Recipe.class));
    }
}
