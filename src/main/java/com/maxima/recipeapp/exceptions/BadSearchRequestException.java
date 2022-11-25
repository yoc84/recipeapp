package com.maxima.recipeapp.exceptions;

public class BadSearchRequestException extends RuntimeException {
    public BadSearchRequestException(String message) {
        super(message);
    }
}
