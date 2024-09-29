package com.college.recipes_collection.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.recipes_collection.dto.requests.BookRequestDTO;
import com.college.recipes_collection.exceptions.BookWithoutIsbnException;
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

        User user = userRepository.findById(request.userId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

        List<Recipe> recipes = recipeRepository.findAllById(request.recipesId());

        book.setTitle(request.title());
        book.setIsbn(request.isbn());
        book.setDescription(request.description());
        book.setUser(user);
        book.setRecipes(recipes);

        bookRepository.save(book);
    }

    public void publishBookById(Long id) {
        Book book = bookRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Book not found"));
        
        if(book.getIsbn().isEmpty() || book.getIsbn() == null) {
            throw new BookWithoutIsbnException();
        }
        
        book.setPublished(true);
        bookRepository.save(book);
    }
}
