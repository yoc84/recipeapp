package com.maxima.recipeapp.exceptions;

public class DuplicateRecipeException extends RuntimeException {
    public DuplicateRecipeException(String message) {
        super(message);
    }
}
