package com.college.recipes_collection.events.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.college.recipes_collection.events.models.BookCreatedEvent;
import com.college.recipes_collection.services.RecipeService;

@Component
public class BookCreatedListener {
    
    @Autowired
    private RecipeService recipeService;

    @EventListener
    public void handleBookCreatedListener(BookCreatedEvent event) {
        recipeService.updateIsPublishedStatus(event.getRecipeId(), true);
    }
}
