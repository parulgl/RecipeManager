package com.mendix.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Amount {

    @JsonProperty("qty")
    private int quantity;

    @JsonProperty("unit")
    private Unit unit;
}
