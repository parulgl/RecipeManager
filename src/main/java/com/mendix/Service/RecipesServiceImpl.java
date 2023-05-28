package com.mendix.Service;

import com.mendix.constants.Constants;
import com.mendix.db.RecipeDao;
import com.mendix.exceptions.CustomBadDataException;
import com.mendix.exceptions.CustomDataNotFoundException;
import com.mendix.exceptions.CustomInvalidParamException;
import com.mendix.models.Recipe;
import com.mendix.models.RecipeCategory;
import com.mendix.models.request.CreateRecipeRequest;
import com.mendix.models.response.CreateRecipeResponse;
import com.mendix.models.response.GetCategoriesResponse;
import com.mendix.models.response.GetRecipeResponse;
import com.mendix.models.response.GetRecipesResponse;
import com.mendix.util.DataTransformer;
import com.mendix.util.DataValidator;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.ext.Provider;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Provider
@Singleton
public class RecipesServiceImpl implements RecipeService {

    @Inject
    private DataValidator dataValidator;

    @Inject
    private RecipeDao recipeDao;

    /**
     * API to get list of all recipes present in database.
     * @return GetRecipesResponse
     */
    @Override
    public GetRecipesResponse getRecipes() {
        try {
            List<Recipe> recipes = recipeDao.getAllRecipes();
            List<GetRecipeResponse> recipeResponses = recipes.stream().map(DataTransformer::toResponse).collect(Collectors.toList());
            return GetRecipesResponse.builder()
                    .recipes(recipeResponses)
                    .message(Constants.SUCCESS_RECIPE_RETURN)
                    .build();
        } catch (CustomDataNotFoundException e) {
            return GetRecipesResponse.builder()
                    .message(e.getMessage())
                    .build();
        }
    }

    /**
     * API to get list of all recipes for a given category.
     * @return GetRecipesResponse
     */
    @Override
    public GetRecipesResponse getRecipesByCategory(RecipeCategory category) {
        try {
            List<Recipe> recipes = recipeDao.getRecipesByCategory(category);
            List<GetRecipeResponse> recipeResponses = recipes.stream().map(DataTransformer::toResponse).collect(Collectors.toList());
            return GetRecipesResponse.builder()
                    .recipes(recipeResponses)
                    .message(Constants.SUCCESS_RECIPE_RETURN)
                    .build();
        } catch (CustomDataNotFoundException e) {
            return GetRecipesResponse.builder()
                    .message(e.getMessage())
                    .build();
        }
    }

    /**
     * API to get list of all recipes that have the search key in either title or category.
     * @return GetRecipesResponse
     */
    @Override
    public GetRecipesResponse searchRecipes(String searchKey) {
        try {
            List<Recipe> recipes = recipeDao.getRecipesBySearchCriteria(searchKey);
            List<GetRecipeResponse> recipeResponses = recipes.stream().map(DataTransformer::toResponse).collect(Collectors.toList());
            return GetRecipesResponse.builder()
                    .recipes(recipeResponses)
                    .message(Constants.SUCCESS_RECIPE_RETURN)
                    .build();
        } catch (CustomDataNotFoundException e) {
            return GetRecipesResponse.builder()
                    .message(e.getMessage())
                    .build();
        }
    }

    /**
     * API to add a new API to the database. Checks for validity of parameters.
     * @return CreateRecipeResponse
     */
    @Override
    public CreateRecipeResponse addRecipe(CreateRecipeRequest createRecipeRequest) {
        try {
            Recipe recipe = DataTransformer.toRecipe(createRecipeRequest);
            dataValidator.validParamCheck(recipe);
            recipeDao.addRecipe(recipe);
            return DataTransformer.toResponse(createRecipeRequest, Constants.SUCCESS_RECIPE_ADD);
        } catch (CustomBadDataException e) {
            String msg = Constants.EXCEPTION_THROWN + e.getMessage();
            return CreateRecipeResponse.builder()
                    .message(msg)
                    .build();
        } catch (CustomInvalidParamException e) {
            String msg = Constants.EXCEPTION_THROWN + e.getMessage();
            return CreateRecipeResponse.builder()
                    .message(msg)
                    .build();
        }
    }

    /**
     * API to get list of all categories present in the database.
     * @return GetCategoriesResponse
     */
    @Override
    public GetCategoriesResponse getAllCategories() {
        try {
            Set<RecipeCategory> recipeCategories = recipeDao.getAllCategories();
            return GetCategoriesResponse.builder()
                    .categories(recipeCategories)
                    .message(Constants.SUCCESS_CATEGORY_RETURN)
                    .build();
        } catch (CustomDataNotFoundException e) {
            return GetCategoriesResponse.builder()
                    .message(e.getMessage())
                    .build();
        }
    }

}
