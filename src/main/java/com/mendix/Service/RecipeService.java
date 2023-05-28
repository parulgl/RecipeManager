package com.mendix.Service;

import com.mendix.models.Recipe;
import com.mendix.models.RecipeCategory;
import com.mendix.models.request.CreateRecipeRequest;
import com.mendix.models.response.CreateRecipeResponse;
import com.mendix.models.response.GetCategoriesResponse;
import com.mendix.models.response.GetRecipesResponse;

public interface RecipeService {

    public GetRecipesResponse getRecipes();

    public GetRecipesResponse getRecipesByCategory(RecipeCategory category);

    public GetRecipesResponse searchRecipes(String searchKey);

    public CreateRecipeResponse addRecipe(CreateRecipeRequest createRecipeRequest);

    public GetCategoriesResponse getAllCategories();
}
