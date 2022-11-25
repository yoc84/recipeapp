package com.maxima.recipeapp.service;

import com.maxima.recipeapp.exceptions.BadSearchRequestException;
import com.maxima.recipeapp.exceptions.DuplicateRecipeException;
import com.maxima.recipeapp.exceptions.OperationNotFoundException;
import com.maxima.recipeapp.exceptions.RecipeNotFoundException;
import com.maxima.recipeapp.model.*;
import com.maxima.recipeapp.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.maxima.recipeapp.model.Constants.*;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Recipe getRecipe(String recipeId) {
        return recipeRepository.findById(recipeId).orElseThrow(() -> new RecipeNotFoundException(String.format(RECIPE_NOT_FOUND, recipeId)));
    }

    @Override
    public List<Recipe> getAllRecipes() {
        List<Recipe> recipeList = recipeRepository.findAll();
        if (recipeList.isEmpty()) {
            throw new RecipeNotFoundException(RECIPE_NOT_FOUND);
        }
        return recipeList;
    }

    @Override
    public Recipe createRecipe(RecipeRequest recipeRequest) {
        Recipe recipe = new Recipe();
        if (recipeRepository.findByName(recipeRequest.getName()).isPresent()) {
            throw new DuplicateRecipeException(String.format(DUPLICATE_RECIPE, recipeRequest.getName()));
        }
        recipe.setName(recipeRequest.getName());
        recipe.setType(recipeRequest.getType());
        recipe.setServings(recipeRequest.getServings());
        recipe.setIngredients(recipeRequest.getIngredients());
        recipe.setInstructions(recipeRequest.getInstructions());
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe updateRecipe(String recipeId, RecipeRequest updateRequest) {
        Recipe updateRecipe = recipeRepository.findById(recipeId).orElseThrow(() -> new RecipeNotFoundException(String.format(RECIPE_NOT_FOUND, recipeId)));

        updateRecipe.setName(updateRequest.getName());
        updateRecipe.setType(updateRequest.getType());
        updateRecipe.setServings(updateRequest.getServings());
        updateRecipe.setIngredients(updateRequest.getIngredients());
        updateRecipe.setInstructions(updateRequest.getInstructions());
        return recipeRepository.save(updateRecipe);
    }

    @Override
    public void deleteRecipe(String recipeId) {
        if (recipeRepository.existsById(recipeId)) {
            recipeRepository.deleteById(recipeId);
        } else {
            throw new RecipeNotFoundException(String.format(RECIPE_NOT_FOUND, recipeId));
        }
    }

    @Override
    public List<Recipe> filterRecipes(SearchRequest searchRequest) {
        validateSearchRequest(searchRequest);
        Query query = buildQuery(searchRequest);
        List<Recipe> recipeList = mongoTemplate.find(query, Recipe.class);
        if (recipeList.isEmpty()) {
            throw new RecipeNotFoundException(RECIPE_NOT_FOUND);
        }
        return recipeList;
    }

    private Query buildQuery(SearchRequest searchRequest) {
        Query query = new Query();
        List<Criteria> criteriaList = new ArrayList<>();
        for (FilterCriteria filterCriteria : searchRequest.getFilterCriteriaList()) {
            switch (filterCriteria.getOperation()) {
                case OPERATION_EQUALS:
                    criteriaList.add(Criteria.where(filterCriteria.getKey()).is(filterCriteria.getValue()));
                    break;
                case OPERATION_NOT_EQUALS:
                    criteriaList.add(Criteria.where(filterCriteria.getKey()).ne(filterCriteria.getValue()));
                    break;
                case OPERATION_INCLUDE:
                    criteriaList.add(Criteria.where(filterCriteria.getKey()).in(filterCriteria.getValue()));
                    break;
                case OPERATION_EXCLUDE:
                    criteriaList.add(Criteria.where(filterCriteria.getKey()).nin(filterCriteria.getValue()));
                    break;
                case OPERATION_CONTAINS:
                    criteriaList.add(Criteria.where(filterCriteria.getKey()).regex(filterCriteria.getValue()));
                    break;
                default:
                    throw new OperationNotFoundException(String.format(OPERATION_NOT_FOUND, filterCriteria.getOperation()));
            }
        }

        query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[criteriaList.size()])));
        return query;
    }

    private void validateSearchRequest(SearchRequest searchRequest) {
        for (FilterCriteria filterCriteria : searchRequest.getFilterCriteriaList()) {
            switch (filterCriteria.getKey()) {
                case MONGODB_FIELD_RECIPE_TYPE:
                    if (!(filterCriteria.getOperation().equals(OPERATION_EQUALS)) && !(filterCriteria.getOperation().equals(OPERATION_NOT_EQUALS))) {
                        throw new BadSearchRequestException(String.format(INVALID_SEARCH, MONGODB_FIELD_RECIPE_TYPE));
                    }
                    break;
                case MONGODB_FIELD_SERVINGS:
                    if (!(filterCriteria.getOperation().equals(OPERATION_EQUALS))) {
                        throw new BadSearchRequestException(String.format(INVALID_SEARCH, MONGODB_FIELD_SERVINGS));
                    }
                    break;
                case MONGODB_FIELD_INGREDIENTS:
                    if (!(filterCriteria.getOperation().equals(OPERATION_INCLUDE)) && !(filterCriteria.getOperation().equals(OPERATION_EXCLUDE))) {
                        throw new BadSearchRequestException((String.format(INVALID_SEARCH, MONGODB_FIELD_INGREDIENTS)));
                    }
                case MONGODB_FIELD_INSTRUCTIONS:
                    if (!(filterCriteria.getOperation().equals(OPERATION_CONTAINS))) {
                        throw new BadSearchRequestException((String.format(INVALID_SEARCH, MONGODB_FIELD_INSTRUCTIONS)));
                    }

                default:
                    throw new BadSearchRequestException(String.format(INVALID_SEARCH, filterCriteria.getKey()));
            }

        }
    }
}
