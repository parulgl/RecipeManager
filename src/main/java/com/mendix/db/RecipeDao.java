package com.mendix.db;

import com.mendix.exceptions.CustomBadDataException;
import com.mendix.exceptions.CustomDataNotFoundException;
import com.mendix.models.Recipe;
import com.mendix.models.RecipeCategory;

import java.util.*;

public interface RecipeDao {

    //recipe APIS
    public List<Recipe> getAllRecipes() throws CustomDataNotFoundException;

    public List<Recipe> getRecipesByCategory(RecipeCategory category) throws CustomDataNotFoundException;

    public List<Recipe> getRecipesBySearchCriteria(String searchKey) throws CustomDataNotFoundException;

    public long addRecipe(Recipe recipe) throws CustomBadDataException;

    //category APIs
    public Set<RecipeCategory> getAllCategories() throws CustomDataNotFoundException;
}
