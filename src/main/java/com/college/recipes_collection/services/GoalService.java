package com.college.recipes_collection.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.recipes_collection.repositories.GoalRepository;
import com.college.recipes_collection.repositories.RoleRepository;

@Service
public class GoalService {
    
    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private RoleRepository roleRepository;

}
