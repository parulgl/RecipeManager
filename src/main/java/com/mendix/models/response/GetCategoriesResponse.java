package com.mendix.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.mendix.models.RecipeCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetCategoriesResponse {

    @JsonProperty("Message")
    String message;

    @JsonProperty("categories")
    private Set<RecipeCategory> categories;

}
