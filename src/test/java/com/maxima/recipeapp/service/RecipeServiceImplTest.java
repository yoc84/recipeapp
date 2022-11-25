package com.maxima.recipeapp.service;

import com.maxima.recipeapp.exceptions.RecipeNotFoundException;
import com.maxima.recipeapp.model.Recipe;
import com.maxima.recipeapp.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private Recipe recipe;

    private String recipeId = "1a2b3c";
    private String recipeName = "Salad";

    @Autowired
    private RecipeService recipeService;

    @Test
    void getRecipeTest() {
        Recipe recipe = new Recipe();
        recipe.setId(recipeId);
        Optional recipeOptional = Optional.of(recipe);
        Mockito.when(recipeRepository.findById(recipeId)).thenReturn(recipeOptional);
        assertEquals(recipeId, recipeRepository.findById(recipeId).get().getId());
    }

    @Test
    void getRecipeNotFoundTest() {
        Mockito.when(recipeRepository.findById(recipeId)).thenThrow(RecipeNotFoundException.class);
        assertThrows(RecipeNotFoundException.class,() -> recipeRepository.findById(recipeId));
    }

    @Test
    void getAllRecipesTest() {
        Recipe recipe = new Recipe();
        recipe.setId(recipeId);
        List<Recipe> recipeList = new ArrayList<>();
        recipeList.add(recipe);
        Mockito.when(recipeRepository.findAll()).thenReturn(recipeList);
        assertEquals(recipeId, recipeRepository.findAll().get(0).getId());
    }


    @Test
    void createRecipe() {
        Recipe recipe = new Recipe();
        recipe.setId(recipeId);
        recipe.setName(recipeName);
        Mockito.when(recipeRepository.save(recipe)).thenReturn(recipe);
        assertEquals(recipeId, recipeRepository.save(recipe).getId());
    }


    @Test
    void updateRecipe() {
        Recipe recipe = new Recipe();
        recipe.setId(recipeId);
        recipe.setName(recipeName);
        Mockito.when(recipeRepository.save(recipe)).thenReturn(recipe);
        assertEquals(recipeName, recipeRepository.save(recipe).getName());
    }

    @Test
    void deleteRecipe() {
    }

    @Test
    void filterRecipes() {
    }
}