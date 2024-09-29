package com.college.recipes_collection.exceptions;

public class RecipeInvalidIdsException extends RuntimeException{

    public RecipeInvalidIdsException(String message) {
        super(message);
    }

    public RecipeInvalidIdsException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
