package com.college.recipes_collection.events.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.college.recipes_collection.events.models.RevenueCreatedEvent;
import com.college.recipes_collection.services.RecipeService;

@Component
public class RevenueCreatedListener {

    @Autowired
    private RecipeService recipeService;
    
    @EventListener
    public void handleRevenueCreatedEvent(RevenueCreatedEvent event) {
        recipeService.updateIsRatingStatus(event.getRecipeId(), true);
    }
}
