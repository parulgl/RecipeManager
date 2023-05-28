package com.mendix.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mendix.models.Directions;
import com.mendix.models.Ingredient;
import com.mendix.models.RecipeCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRecipeRequest {

    @JsonProperty("title")
    private String title;

    @JsonProperty("categories")
    private List<RecipeCategory> categories;

    @JsonProperty("yield")
    private int yield;

    @JsonProperty("ingredients")
    private List<Ingredient> ingredients;

    @JsonProperty("directions")
    private Directions directions;

}
