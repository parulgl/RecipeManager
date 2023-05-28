package com.mendix.db;

import com.mendix.constants.Constants;
import com.mendix.exceptions.CustomBadDataException;
import com.mendix.exceptions.CustomDataNotFoundException;
import com.mendix.models.Recipe;
import com.mendix.models.RecipeCategory;
import com.mendix.util.DaoHelper;
import com.mendix.util.DataValidator;
import jakarta.inject.Inject;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Within the scope of this implementation, making use of InMemory
 */
public class RecipeDaoInMemoryImpl implements RecipeDao {

    private long lastRecipeId;
    private final List<Recipe> recipes;
    private Map<RecipeCategory, List<Recipe>> categoryToRecipeMap;

    @Inject
    private DataValidator dataValidator;

    public RecipeDaoInMemoryImpl() {
        this.recipes = new ArrayList<>();
        this.categoryToRecipeMap = new HashMap<>();
    }

    public List<Recipe> getAllRecipes() throws CustomDataNotFoundException {

        if (this.recipes.isEmpty()) {
            throw new CustomDataNotFoundException(Constants.RECIPE_NOT_FOUND);
        }
        return this.recipes;
    }

    public List<Recipe> getRecipesByCategory(RecipeCategory category) throws CustomDataNotFoundException {
        if (!categoryToRecipeMap.containsKey(category)) {
            throw new CustomDataNotFoundException(Constants.RECIPE_NOT_FOUND);
        } else {
            return categoryToRecipeMap.get(category);
        }
    }

    @Override
    public List<Recipe> getRecipesBySearchCriteria(String searchKey) throws CustomDataNotFoundException {
        List<Recipe> recipeList = recipes.stream().filter(x -> DaoHelper.search(x, searchKey) != null).collect(Collectors.toList());
        if (recipeList.isEmpty()) {
            throw new CustomDataNotFoundException(Constants.RECIPE_NOT_FOUND);
        }
        return recipeList;
    }

    @Override
    public long addRecipe(Recipe recipe) throws CustomBadDataException {

        dataValidator.uniqueTitleCheck(recipes, recipe);
        recipe.setId(++lastRecipeId);
        recipes.add(recipe);

        for (RecipeCategory category : recipe.getCategories()) {
            if (!categoryToRecipeMap.containsKey(category)) {
                List<Recipe> lst = new ArrayList<>();
                lst.add(recipe);
                categoryToRecipeMap.put(category, lst);
            } else {
                categoryToRecipeMap.get(category).add(recipe);
            }
        }

        return lastRecipeId;
    }

    @Override
    public Set<RecipeCategory> getAllCategories() throws CustomDataNotFoundException {
        Set<RecipeCategory> categories = categoryToRecipeMap.keySet();
        if (categories.isEmpty())  {
            throw new CustomDataNotFoundException(Constants.CATEGORY_NOT_FOUND);
        }
        return categories;
    }
}
