package com.maxima.recipeapp.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("recipeCollection")
public class Recipe {

    @Id
    private String id;

    private String name;
    private String type;
    private int servings;
    private List<String> ingredients;
    private String instructions;

}
