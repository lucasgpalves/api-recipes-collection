package com.college.recipes_collection.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.recipes_collection.models.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
    
}
