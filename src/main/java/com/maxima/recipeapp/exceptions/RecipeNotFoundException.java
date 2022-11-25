package com.maxima.recipeapp.exceptions;

public class RecipeNotFoundException extends RuntimeException {

    public RecipeNotFoundException(String msg) {
        super(msg);
    }

}
