package com.mendix.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class GetRecipesResponse {

    @JsonProperty("Message")
    String message;

    @JsonProperty("recipes")
    List<GetRecipeResponse> recipes;

}
