package com.mendix.util;

import com.mendix.constants.Constants;
import com.mendix.exceptions.CustomBadDataException;
import com.mendix.exceptions.CustomInvalidParamException;
import com.mendix.models.Recipe;
import com.mendix.models.RecipeCategory;

import java.util.List;

/**
 * Util class for data validation
 */
public class DataValidator {

    public boolean uniqueTitleCheck(List<Recipe> recipeList, Recipe recipe) throws CustomBadDataException {
        for (Recipe rec : recipeList) {
            if (rec.getTitle().equalsIgnoreCase(recipe.getTitle())) {
                throw new CustomBadDataException(Constants.EXCEPTION_TITLE_EXISTS);
            }
        }
        return true;
    }

    public boolean validParamCheck(Recipe recipe) throws CustomInvalidParamException {
        if (recipe.getTitle() == null || recipe.getCategories() == null ||
                recipe.getTitle().isEmpty() || recipe.getCategories().isEmpty()) {
            throw new CustomInvalidParamException(Constants.EXCEPTION_INVALID_PARAM);
        }
        return true;
    }
}
