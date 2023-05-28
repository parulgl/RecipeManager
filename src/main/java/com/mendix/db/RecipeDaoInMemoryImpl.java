package com.mendix.db;

import com.mendix.exceptions.CustomBadDataException;
import com.mendix.exceptions.CustomDataNotFoundException;
import com.mendix.models.Recipe;
import com.mendix.models.RecipeCategory;
import com.mendix.util.DataValidator;
import jakarta.inject.Inject;

import java.util.*;
import java.util.stream.Collectors;


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
            throw new CustomDataNotFoundException("No recipe found");
        }
        return this.recipes;
    }

    public List<Recipe> getRecipesByCategory(RecipeCategory category) throws CustomDataNotFoundException {
        if (!categoryToRecipeMap.containsKey(category)) {
            throw new CustomDataNotFoundException("No recipe found for this category");
        } else {
            return categoryToRecipeMap.get(category);
        }
    }

    @Override
    public List<Recipe> getRecipesBySearchCriteria(String searchKey) throws CustomDataNotFoundException {
        List<Recipe> recipeList = recipes.stream().filter(x -> DaoHelper.search(x, searchKey) != null).collect(Collectors.toList());
        if (recipeList.isEmpty()) {
            throw new CustomDataNotFoundException("No recipe found for this search word");
        }
        return recipeList;
    }

    @Override
    public long addRecipe(Recipe recipe) throws CustomBadDataException {

        if (!dataValidator.uniqueTitleCheck(recipes, recipe)) {
            throw new CustomBadDataException("Title already exists");
        }
        if (!dataValidator.validParamCheck(recipe)) {
            throw new CustomBadDataException("Invalid parameters");
        }
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
            throw new CustomDataNotFoundException("No category found");
        }
        return categories;
    }
}
