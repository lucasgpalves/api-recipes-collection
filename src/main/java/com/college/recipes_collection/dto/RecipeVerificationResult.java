package com.college.recipes_collection.dto;

import com.college.recipes_collection.models.User;

public class RecipeVerificationResult {
    private boolean exists;
    private User user;

    // Construtor
    public RecipeVerificationResult(boolean exists, User user) {
        this.exists = exists;
        this.user = user;
    }

    // Getters
    public boolean exists() {
        return exists;
    }

    public User getUser() {
        return user;
    }
}
