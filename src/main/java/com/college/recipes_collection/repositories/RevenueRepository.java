package com.college.recipes_collection.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.recipes_collection.models.Revenue;

public interface RevenueRepository extends JpaRepository<Revenue, Long>{
    
}
