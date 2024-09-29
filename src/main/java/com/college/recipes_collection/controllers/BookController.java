package com.college.recipes_collection.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.college.recipes_collection.dto.requests.BookRequestDTO;
import com.college.recipes_collection.services.BookService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BookController {
    
    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Void> createBook(@Valid @RequestBody BookRequestDTO request) {
        bookService.createBook(request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> publishBookById(@PathVariable Long id) {
        bookService.publishBookById(id);
        return ResponseEntity.ok().build();
    }

}
