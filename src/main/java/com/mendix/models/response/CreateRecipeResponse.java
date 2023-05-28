package com.mendix.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mendix.models.Directions;
import com.mendix.models.Ingredient;
import com.mendix.models.RecipeCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@Builder
@AllArgsConstructor
public class CreateRecipeResponse {

    @JsonProperty("Message")
    String message;

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
