package com.maxima.recipeapp.service;

import com.maxima.recipeapp.model.Recipe;
import com.maxima.recipeapp.model.RecipeRequest;
import com.maxima.recipeapp.model.SearchRequest;

import java.util.List;

public interface RecipeService {

    Recipe getRecipe(String recipeId);

    List<Recipe> getAllRecipes();

    Recipe createRecipe(RecipeRequest recipeRequest);

    Recipe updateRecipe(String recipeId, RecipeRequest recipeRequest);

    void deleteRecipe(String recipeId);

    List<Recipe> filterRecipes(SearchRequest searchRequest);

}
