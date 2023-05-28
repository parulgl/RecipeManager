package com.mendix.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {

    @JsonProperty("amt")
    private Amount amount;

    @JsonProperty("item")
    private String item;
}
