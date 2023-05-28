package com.mendix.Service;

import com.mendix.db.RecipeDao;
import com.mendix.exceptions.CustomBadDataException;
import com.mendix.exceptions.CustomDataNotFoundException;
import com.mendix.models.Recipe;
import com.mendix.models.RecipeCategory;
import com.mendix.models.request.CreateRecipeRequest;
import com.mendix.models.response.CreateRecipeResponse;
import com.mendix.models.response.GetCategoriesResponse;
import com.mendix.models.response.GetRecipeResponse;
import com.mendix.models.response.GetRecipesResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.ext.Provider;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Provider
@Singleton
public class RecipesServiceImpl implements RecipeService {

    private RecipeDao recipeDao;

    @Inject
    public RecipesServiceImpl(RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
    }

    @Override
    public GetRecipesResponse getRecipes() {
        try {
            List<Recipe> recipes = recipeDao.getAllRecipes();
            List<GetRecipeResponse> recipeResponses = recipes.stream().map(DataTransformer::toResponse).collect(Collectors.toList());
            String msg = "Recipes returned successfully";
            return GetRecipesResponse.builder().recipes(recipeResponses).message(msg).build();
        } catch (CustomDataNotFoundException e) {
            return GetRecipesResponse.builder().message(e.getMessage()).build();
        }
    }

    @Override
    public GetRecipesResponse getRecipesByCategory(RecipeCategory category) {
        try {
            List<Recipe> recipes = recipeDao.getRecipesByCategory(category);
            List<GetRecipeResponse> recipeResponses = recipes.stream().map(DataTransformer::toResponse).collect(Collectors.toList());
            String msg = "Recipes returned successfully for category " + category.toString();
            return GetRecipesResponse.builder().recipes(recipeResponses).message(msg).build();
        } catch (CustomDataNotFoundException e) {
            return GetRecipesResponse.builder().message(e.getMessage()).build();
        }
    }

    @Override
    public GetRecipesResponse searchRecipes(String searchKey) {
        try {
            List<Recipe> recipes = recipeDao.getRecipesBySearchCriteria(searchKey);
            List<GetRecipeResponse> recipeResponses = recipes.stream().map(DataTransformer::toResponse).collect(Collectors.toList());
            String msg = "Recipes returned successfully";
            return GetRecipesResponse.builder().recipes(recipeResponses).message(msg).build();
        } catch (CustomDataNotFoundException e) {
            return GetRecipesResponse.builder().message(e.getMessage()).build();
        }
    }

    @Override
    public CreateRecipeResponse addRecipe(CreateRecipeRequest createRecipeRequest) {
        try {
            Recipe recipe = DataTransformer.toRecipe(createRecipeRequest);
            recipeDao.addRecipe(recipe);
            String msg = "Recipe added successfully!";
            return DataTransformer.toResponse(createRecipeRequest, msg);
        } catch (CustomBadDataException e) {
            String msg = "Exception thrown for this request : " + e.getMessage();
            return CreateRecipeResponse.builder().message(msg).build();
        }
    }

    @Override
    public GetCategoriesResponse getAllCategories() {
        try {
            Set<RecipeCategory> recipeCategories = recipeDao.getAllCategories();
            String msg = "Categories returned successfully";
            return GetCategoriesResponse.builder().categories(recipeCategories).message(msg).build();
        } catch (CustomDataNotFoundException e) {
            return GetCategoriesResponse.builder().message(e.getMessage()).build();
        }
    }

}
