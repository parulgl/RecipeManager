package com.mendix.util;

import com.mendix.constants.TestData;
import com.mendix.exceptions.CustomBadDataException;
import com.mendix.exceptions.CustomInvalidParamException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataValidatorTest {

    private DataValidator dataValidator;

    @BeforeEach
    public void setup() {
        this.dataValidator = new DataValidator();
    }

    @Test
    public void uniqueTitleCheckTestSuccess() {
        Assertions.assertDoesNotThrow(()->dataValidator.uniqueTitleCheck(TestData.getRecipesList(), TestData.getRecipe2()));
    }

    @Test
    public void uniqueTitleCheckTestFail() {
        Assertions.assertThrows(CustomBadDataException.class,
                ()->dataValidator.uniqueTitleCheck(TestData.getRecipesList(), TestData.getRecipe()));
    }

    @Test
    public void validParamCheckTestSuccess() {
        Assertions.assertDoesNotThrow(()->dataValidator.validParamCheck(TestData.getRecipe()));
    }

    @Test
    public void validParamCheckTestFail() {
        Assertions.assertThrows(CustomInvalidParamException.class,
                ()->dataValidator.validParamCheck(TestData.getInvalidRecipe()));
    }

}
