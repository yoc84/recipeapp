package com.maxima.recipeapp.model;

import lombok.*;
import org.apache.commons.lang3.builder.HashCodeExclude;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class SearchRequest {

    @NotEmpty
    private List<FilterCriteria> filterCriteriaList;

}
