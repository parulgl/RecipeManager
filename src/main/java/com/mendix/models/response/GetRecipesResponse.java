package com.mendix.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@Builder
@AllArgsConstructor
public class GetRecipesResponse {

    @JsonProperty("Message")
    String message;

    @JsonProperty("recipes")
    List<GetRecipeResponse> recipes;

}
