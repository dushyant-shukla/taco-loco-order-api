package com.taco.loco.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class OrderModel {

    @JsonProperty("employee-id")
    @NonNull
    private long employeeID;

    @JsonProperty("items")
    @NonNull
    private List<MenuItemModel> items;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("total-price")
    private double priceBeforeDiscout;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("discounted-price")
    private double priceAfterDiscount;
}
