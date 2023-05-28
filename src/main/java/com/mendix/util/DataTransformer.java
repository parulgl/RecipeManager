package com.mendix.util;

import com.mendix.models.Recipe;
import com.mendix.models.request.CreateRecipeRequest;
import com.mendix.models.response.CreateRecipeResponse;
import com.mendix.models.response.GetRecipeResponse;
import com.mendix.models.response.GetRecipesResponse;

public class DataTransformer {

    public static GetRecipeResponse toResponse(Recipe recipe) {
        return GetRecipeResponse.builder()
                .id(recipe.getId())
                .title(recipe.getTitle())
                .yield(recipe.getYield())
                .categories(recipe.getCategories())
                .ingredients(recipe.getIngredients())
                .directions(recipe.getDirections())
                .build();
    }

    public static CreateRecipeResponse toResponse(CreateRecipeRequest createRecipeRequest, String message) {
        return CreateRecipeResponse.builder()
                .title(createRecipeRequest.getTitle())
                .yield(createRecipeRequest.getYield())
                .categories(createRecipeRequest.getCategories())
                .directions(createRecipeRequest.getDirections())
                .ingredients(createRecipeRequest.getIngredients())
                .message(message)
                .build();
    }

    public static Recipe toRecipe(CreateRecipeRequest createRecipeRequest) {
        return Recipe.builder()
                .categories(createRecipeRequest.getCategories())
                .title(createRecipeRequest.getTitle())
                .yield(createRecipeRequest.getYield())
                .ingredients(createRecipeRequest.getIngredients())
                .directions(createRecipeRequest.getDirections())
                .build();
    }
}
