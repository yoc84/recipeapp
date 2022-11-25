package com.maxima.recipeapp.model;

import lombok.*;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class FilterCriteria {

    @NotBlank
    private String key;
    @NotBlank
    private String value;
    @NotBlank
    private String operation;

}
