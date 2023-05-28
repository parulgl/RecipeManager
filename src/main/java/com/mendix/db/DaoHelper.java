package com.mendix.db;

import com.mendix.models.Recipe;
import com.mendix.models.RecipeCategory;

public class DaoHelper {

    /**
     * This method supports searching only in the title and the categories
     * @param recipe to search in
     * @param searchKey to search for
     * @return
     */
    public static Recipe search(Recipe recipe, String searchKey) {

        if (recipe.getTitle().contains(searchKey)) {
            return recipe;
        }

        for(RecipeCategory category : recipe.getCategories()) {
            if (category.toString().contains(searchKey)) {
                return recipe;
            }
        }

        return null;
    }
}
