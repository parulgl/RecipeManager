package com.mendix.Service;

import com.mendix.constants.TestData;
import com.mendix.db.RecipeDao;
import com.mendix.exceptions.CustomBadDataException;
import com.mendix.exceptions.CustomDataNotFoundException;
import com.mendix.exceptions.CustomInvalidParamException;
import com.mendix.models.RecipeCategory;
import com.mendix.models.response.CreateRecipeResponse;
import com.mendix.models.response.GetCategoriesResponse;
import com.mendix.models.response.GetRecipesResponse;
import com.mendix.util.DataValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


public class RecipesServiceTest {

    @InjectMocks
    private RecipeService recipesService;

    @Mock
    private DataValidator dataValidator;

    @Mock
    private RecipeDao recipeDao;

    private static final String SUCCESS_MSG = "Recipes returned successfully";
    private static final String RECIPE_NOT_FOUND = "No recipe found";
    private static final String RECIPE_ADD_SUCCESS = "Recipe added successfully";
    private static final String INVALID_PARAM = "Empty Title or Category field for Recipe";
    private static final String EXCEPTION = "Exception thrown for this request : ";
    public static final String SUCCESS_CATEGORY_RETURN = "Categories returned successfully";


    @BeforeEach
    public void setup() {
        this.recipesService = new RecipesServiceImpl();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getRecipesTestSuccess() {
        Mockito.when(recipeDao.getAllRecipes()).thenReturn(TestData.getRecipesList());
        GetRecipesResponse response = recipesService.getRecipes();
        Assertions.assertDoesNotThrow(()->recipesService.getRecipes());
        Assertions.assertEquals(SUCCESS_MSG, response.getMessage());
    }

    @Test
    public void getRecipesTest_NoRecipeFound() {
        Mockito.when(recipeDao.getAllRecipes()).thenThrow(new CustomDataNotFoundException(RECIPE_NOT_FOUND));
        GetRecipesResponse response = recipesService.getRecipes();
        Assertions.assertDoesNotThrow(()->recipesService.getRecipes());
        Assertions.assertEquals(RECIPE_NOT_FOUND, response.getMessage());
    }

    @Test
    public void getRecipesByCategoryTestSuccess() {
        Mockito.when(recipeDao.getRecipesByCategory(RecipeCategory.MAIN_DISH)).thenReturn(TestData.getRecipesList());
        GetRecipesResponse response = recipesService.getRecipesByCategory(RecipeCategory.MAIN_DISH);
        Assertions.assertDoesNotThrow(()->recipesService.getRecipesByCategory(RecipeCategory.MAIN_DISH));
        Assertions.assertEquals(SUCCESS_MSG, response.getMessage());
    }

    @Test
    public void getRecipesByCategoryTest_NoRecipeFound() {
        Mockito.when(recipeDao.getRecipesByCategory(RecipeCategory.MAIN_DISH))
                .thenThrow(new CustomDataNotFoundException(RECIPE_NOT_FOUND));
        GetRecipesResponse response = recipesService.getRecipesByCategory(RecipeCategory.MAIN_DISH);
        Assertions.assertDoesNotThrow(()->recipesService.getRecipesByCategory(RecipeCategory.MAIN_DISH));
        Assertions.assertEquals(RECIPE_NOT_FOUND, response.getMessage());
    }

    @Test
    public void searchRecipesTestSuccess() {
        Mockito.when(recipeDao.getRecipesBySearchCriteria("MAIN")).thenReturn(TestData.getRecipesList());
        GetRecipesResponse response = recipesService.searchRecipes("MAIN");
        Assertions.assertDoesNotThrow(()->recipesService.searchRecipes("MAIN"));
        Assertions.assertEquals(SUCCESS_MSG, response.getMessage());
    }

    @Test
    public void searchRecipesTest_NoRecipeFound() {
        Mockito.when(recipeDao.getRecipesBySearchCriteria("MAIN"))
                .thenThrow(new CustomDataNotFoundException(RECIPE_NOT_FOUND));
        GetRecipesResponse response = recipesService.searchRecipes("MAIN");
        Assertions.assertDoesNotThrow(()->recipesService.searchRecipes("MAIN"));
        Assertions.assertEquals(RECIPE_NOT_FOUND, response.getMessage());
    }

    @Test
    public void addRecipeTestSuccess() {
        Mockito.when(dataValidator.validParamCheck(TestData.getRecipe())).thenReturn(true);
        Mockito.when(recipeDao.addRecipe(TestData.getRecipe())).thenReturn(1l);
        CreateRecipeResponse response = recipesService.addRecipe(TestData.createRecipeRequest());
        Assertions.assertDoesNotThrow(()->recipesService.addRecipe(TestData.createRecipeRequest()));
        Assertions.assertEquals(RECIPE_ADD_SUCCESS, response.getMessage());
    }

    @Test
    public void addRecipeTest_TitleNotUnique() {
        Mockito.when(dataValidator.validParamCheck(TestData.getRecipe())).thenReturn(true);
        Mockito.when(recipeDao.addRecipe(Mockito.any())).thenThrow(new CustomBadDataException(INVALID_PARAM));
        CreateRecipeResponse response = recipesService.addRecipe(TestData.createRecipeRequest());
        Assertions.assertDoesNotThrow(()->recipesService.addRecipe(TestData.createRecipeRequest()));
        Assertions.assertEquals(EXCEPTION + INVALID_PARAM, response.getMessage());
    }

    @Test
    public void addRecipeTest_InvalidParam() {
        Mockito.when(dataValidator.validParamCheck(Mockito.any()))
                .thenThrow(new CustomInvalidParamException(INVALID_PARAM));
        CreateRecipeResponse response = recipesService.addRecipe(TestData.createRecipeRequest());
        Assertions.assertDoesNotThrow(()->recipesService.addRecipe(TestData.createRecipeRequest()));
        Assertions.assertEquals(EXCEPTION + INVALID_PARAM, response.getMessage());
    }

    @Test
    public void getAllCategoriesTestSuccess() {
        Mockito.when(recipeDao.getAllCategories()).thenReturn(TestData.getCategories());
        GetCategoriesResponse response = recipesService.getAllCategories();
        Assertions.assertDoesNotThrow(()->recipesService.getAllCategories());
        Assertions.assertEquals(SUCCESS_CATEGORY_RETURN, response.getMessage());
    }

    @Test
    public void getAllCategoriesTestSuccess_NoCategoryFound() {
        Mockito.when(recipeDao.getAllCategories()).thenThrow(new CustomDataNotFoundException(RECIPE_NOT_FOUND));
        GetCategoriesResponse response = recipesService.getAllCategories();
        Assertions.assertDoesNotThrow(()->recipesService.getRecipes());
        Assertions.assertEquals(RECIPE_NOT_FOUND, response.getMessage());
    }
}
