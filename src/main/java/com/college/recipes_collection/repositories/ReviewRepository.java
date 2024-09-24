package com.college.recipes_collection.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.recipes_collection.models.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{
    
}
