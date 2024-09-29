package com.college.recipes_collection.exceptions;

public class BookWithoutIsbnException extends RuntimeException{
    public BookWithoutIsbnException(){
        super("This book does not have an ISBN.");
    }
    
    public BookWithoutIsbnException(String message) {
        super(message);
    }
}
