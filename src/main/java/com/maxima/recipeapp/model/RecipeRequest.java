package com.maxima.recipeapp.model;

import lombok.*;

import javax.validation.constraints.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class RecipeRequest {

    @NotBlank @Size(min=2, max=30)
    private String name;
    @NotBlank
    private String type;
    @Min(1) @Max(20)
    private int servings;
    @NotEmpty
    private List<String> ingredients;
    @NotBlank
    private String instructions;

}
