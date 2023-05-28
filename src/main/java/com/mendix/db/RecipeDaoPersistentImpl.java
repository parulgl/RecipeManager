package com.mendix.db;

import com.mendix.exceptions.CustomBadDataException;
import com.mendix.models.Recipe;
import com.mendix.models.RecipeCategory;

import java.util.List;
import java.util.Set;

/**
 * Current implementation can be scaled to use persistent database.
 */
public class RecipeDaoPersistentImpl implements RecipeDao {
    @Override
    public List<Recipe> getAllRecipes() {
        return null;
    }

    @Override
    public List<Recipe> getRecipesByCategory(RecipeCategory category) {
        return null;
    }

    @Override
    public List<Recipe> getRecipesBySearchCriteria(String searchKey) {
        return null;
    }

    @Override
    public long addRecipe(Recipe recipe) throws CustomBadDataException {
        return 0l;
    }

    @Override
    public Set<RecipeCategory> getAllCategories() {
        return null;
    }
}
