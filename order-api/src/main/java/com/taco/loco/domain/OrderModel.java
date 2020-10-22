package com.taco.loco.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderModel implements Cloneable {

    @NotNull(message = "items may not be null")
    @NotEmpty(message = "items may not be empty")
    @JsonProperty("items")
    @NonNull
    @Valid
    private List<MenuItemModel> items = new ArrayList<>();

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("total-price")
    private double priceBeforeDiscount;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("discounted-price")
    private double priceAfterDiscount;

    @Override
    public Object clone() throws CloneNotSupportedException {
        OrderModel clone = new OrderModel();
        clone.priceAfterDiscount = this.priceAfterDiscount;
        clone.priceBeforeDiscount = this.priceBeforeDiscount;
        for(MenuItemModel item : this.items) {
            clone.items.add(new MenuItemModel(item));
        }
        return clone;
    }
}
