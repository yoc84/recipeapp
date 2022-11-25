package com.maxima.recipeapp.repository;

import com.maxima.recipeapp.model.Recipe;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class RecipeRepositoryTest {
    static {
        System.setProperty("spring.mongodb.embedded.version","5.0.0");
    }
    @Autowired
    private RecipeRepository recipeRepository;

    @Test
    void findByNameTest() {
        Recipe recipe = new Recipe();
        String name = "Salad";
        recipe.setName(name);
        recipeRepository.save(recipe);
        Optional<Recipe> result = recipeRepository.findByName(name);
        assertEquals(name,
                result.get().getName());
    }
}