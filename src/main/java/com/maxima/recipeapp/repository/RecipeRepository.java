package com.maxima.recipeapp.repository;

import com.maxima.recipeapp.model.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipeRepository extends MongoRepository<Recipe, String> {

    @Query("{name:?0}")
    Optional<Recipe> findByName(String name);

}
