package com.mendix.resources;

import com.mendix.Service.RecipeService;
import com.mendix.models.RecipeCategory;
import com.mendix.models.request.CreateRecipeRequest;
import com.mendix.models.response.CreateRecipeResponse;
import com.mendix.models.response.GetCategoriesResponse;
import com.mendix.models.response.GetRecipesResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/recipe")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Singleton
public class RecipesResource {

    @Inject
    private RecipeService recipeService;

    @GET
    public GetRecipesResponse getRecipes() {
        return recipeService.getRecipes();
    }

    @GET
    @Path("/category/{category}")
    public GetRecipesResponse getRecipesByCategory(
            @PathParam("category") @NotNull RecipeCategory recipeCategory) {
        return recipeService.getRecipesByCategory(recipeCategory);
    }

    @GET
    @Path("/search/{search_key}")
    public GetRecipesResponse searchRecipe(@PathParam("search_key") @NotNull String searchKey) {
        return recipeService.searchRecipes(searchKey);
    }

    @POST
    public CreateRecipeResponse addRecipe(CreateRecipeRequest createRecipeRequest) {
        return recipeService.addRecipe(createRecipeRequest);
    }

    @GET
    @Path("/category")
    public GetCategoriesResponse getCategories() {
        return recipeService.getAllCategories();
    }
}
