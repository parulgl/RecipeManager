package com.mendix.util;

import com.mendix.models.Recipe;

import java.util.List;

public class DataValidator {

    public boolean uniqueTitleCheck(List<Recipe> recipeList, Recipe recipe) {
        for (Recipe rec : recipeList) {
            if (rec.getTitle().equalsIgnoreCase(recipe.getTitle())) {
                return false;
            }
        }
        return true;
    }

    public boolean validParamCheck(Recipe recipe) {
        if (recipe.getTitle().isEmpty()) {
            return false;
        } else if (recipe.getCategories().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

}
