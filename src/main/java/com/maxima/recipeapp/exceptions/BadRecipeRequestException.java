package com.maxima.recipeapp.exceptions;

public class BadRecipeRequestException extends RuntimeException {
    public BadRecipeRequestException(String message) {
        super(message);
    }
}
