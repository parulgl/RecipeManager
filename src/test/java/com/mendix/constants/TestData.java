package com.mendix.constants;

import com.mendix.models.Recipe;
import com.mendix.models.RecipeCategory;
import com.mendix.models.request.CreateRecipeRequest;
import com.mendix.models.response.CreateRecipeResponse;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestData {

    private static final List<RecipeCategory> CATEGORY_LIST = Arrays.asList(new RecipeCategory[]{RecipeCategory.MAIN_DISH});

    private static final Recipe RECIPE_1 = Recipe.builder()
            .id(1l)
            .categories(CATEGORY_LIST)
            .title("TestRecipe_1")
            .build();

    private static final Recipe RECIPE_2 = Recipe.builder()
            .id(1l)
            .categories(CATEGORY_LIST)
            .title("TestRecipe_2")
            .build();

    private static final Recipe RECIPE_3 = Recipe.builder()
            .id(1l)
            .categories(CATEGORY_LIST)
            .title("")
            .build();

    public static List<Recipe> getRecipesList() {
        return Arrays.asList(new Recipe[]{RECIPE_1});
    }

    public static CreateRecipeRequest createRecipeRequest() {
        return CreateRecipeRequest.builder()
                .categories(CATEGORY_LIST)
                .title("TestRecipe_1")
                .build();
    }

    public static Recipe getRecipe() {
        return RECIPE_1;
    }

    public static Recipe getRecipe2() {
        return RECIPE_2;
    }

    public static Recipe getInvalidRecipe() { return RECIPE_3; }

    public static CreateRecipeResponse createRecipeResponse() {
        return CreateRecipeResponse.builder()
                .categories(CATEGORY_LIST)
                .title("TestRecipe_1")
                .build();
    }

    public static Set<RecipeCategory> getCategories() {
        Set<RecipeCategory> categorySet = new HashSet<>();
        categorySet.add(RecipeCategory.MAIN_DISH);
        return categorySet;
    }

}
