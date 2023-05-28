package com.mendix.models;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    private Long id;
    private String title;
    private List<RecipeCategory> categories;
    private int yield;
    private List<Ingredient> ingredients;
    private Directions directions;
}
