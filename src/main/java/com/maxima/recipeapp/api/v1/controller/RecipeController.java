package com.maxima.recipeapp.api.v1.controller;

import com.maxima.recipeapp.model.Recipe;
import com.maxima.recipeapp.model.RecipeRequest;
import com.maxima.recipeapp.model.SearchRequest;
import com.maxima.recipeapp.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("api/v1/recipe")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/{recipeId}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable @NotBlank String recipeId) {
        return new ResponseEntity<>(recipeService.getRecipe(recipeId), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        return new ResponseEntity<>(recipeService.getAllRecipes(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Recipe> createRecipe(@Valid @RequestBody RecipeRequest recipeRequest) {
        return new ResponseEntity<>(recipeService.createRecipe(recipeRequest), HttpStatus.CREATED);
    }

    @PatchMapping("/{recipeId}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable @NotBlank String recipeId, @Valid @RequestBody RecipeRequest recipeRequest) {
        return new ResponseEntity<>(recipeService.updateRecipe(recipeId, recipeRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{recipeId}")
    public ResponseEntity<String> deleteRecipe(@PathVariable @NotBlank String recipeId) {
        recipeService.deleteRecipe(recipeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Recipe>> searchRecipe(@Valid @RequestBody SearchRequest searchRequest) {
        return new ResponseEntity<>(recipeService.filterRecipes(searchRequest), HttpStatus.OK);
    }


}
