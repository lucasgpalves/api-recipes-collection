package com.college.recipes_collection.services;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.recipes_collection.dto.RecipeNameDTO;
import com.college.recipes_collection.dto.requests.BookRequestDTO;
import com.college.recipes_collection.dto.responses.BookResponseDTO;
import com.college.recipes_collection.exceptions.BookWithoutIsbnException;
import com.college.recipes_collection.exceptions.RecipeInvalidIdsException;
import com.college.recipes_collection.models.Book;
import com.college.recipes_collection.models.Recipe;
import com.college.recipes_collection.models.User;
import com.college.recipes_collection.repositories.BookRepository;
import com.college.recipes_collection.repositories.RecipeRepository;
import com.college.recipes_collection.repositories.UserRepository;

@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    public void createBook(BookRequestDTO request) {
        Book book = new Book();

        User user = findUserById(request.userId());

        List<Recipe> recipes = recipeRepository.findAllById(request.recipesId());
        validateRecipeIds(request.recipesId(), recipes);
        
        book.setTitle(request.title());
        book.setIsbn(request.isbn());
        book.setDescription(request.description());
        book.setUser(user);
        book.setRecipes(recipes);
        book.setPublished(false);

        bookRepository.save(book);
    }

    public List<BookResponseDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(book -> new BookResponseDTO(
                    book.getTitle(), 
                    book.getUser().getName(), 
                    (book.getIsbn() == null || book.getIsbn().isEmpty() ? null : book.getIsbn()), 
                    book.getDescription(), 
                    mapToRecipeNameDTOs(book)
                )).collect(Collectors.toList());
    }

    public BookResponseDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Book not found"));

        List<RecipeNameDTO> recipes = mapToRecipeNameDTOs(book);
        
        return new BookResponseDTO( 
            book.getTitle(), 
            book.getUser().getName(), 
            (book.getIsbn() == null || book.getIsbn().isEmpty() ? null : book.getIsbn()), 
            book.getDescription(), 
            recipes);
    }

    public void publishBookById(Long id) {
        Book book = bookRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Book not found"));
        
        if(book.getIsbn().isEmpty() || book.getIsbn() == null) {
            throw new BookWithoutIsbnException();
        }
        
        //Adicionar um lógica para marcar como publicado cada receita relacionada

        book.setPublished(true);
        bookRepository.save(book);
    }

    private void validateRecipeIds(List<Long> requestedIds, List<Recipe> foundRecipes) {
        Set<Long> foundIds = foundRecipes.stream()
                                .map(Recipe::getId)
                                .collect(Collectors.toSet());
        
        Set<Long> invalidIds = new HashSet<>(requestedIds);
        invalidIds.removeAll(foundIds);
        if(!invalidIds.isEmpty()) {
            throw new RecipeInvalidIdsException("Invalid recipe IDs: " + invalidIds);
        }
    }

    private List<RecipeNameDTO> mapToRecipeNameDTOs(Book book) {
        return book.getRecipes().stream()
                .map(recipe -> new RecipeNameDTO(
                    recipe.getName()
                ))
                .collect(Collectors.toList());
    }

    private User findUserById(Long id) {
        return userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
