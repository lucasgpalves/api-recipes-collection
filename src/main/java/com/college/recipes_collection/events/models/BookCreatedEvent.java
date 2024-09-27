package com.college.recipes_collection.events.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookCreatedEvent {
    private Long recipeId;
}
