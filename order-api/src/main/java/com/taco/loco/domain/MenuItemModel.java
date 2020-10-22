package com.taco.loco.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.taco.loco.entity.MenuItem;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class MenuItemModel {

    @Min(value = 1, message = "minimum value for 'id' is 1")
    @NonNull
    private long id;

    @NotBlank(message = "'name' must not be blank")
    @NonNull
    private String name;

    @Min(value = 1, message = "minimum value for 'price' is 1")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private double price;

    @Min(value = 1, message = "minimum value for 'quantity' is 1")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int quantity;

    public MenuItemModel(MenuItemModel another) {
        this.id = another.id;
        this.name = another.name;
        this.price = another.price;
        this.quantity = another.quantity;
    }

    public MenuItemModel(long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object another) {
        if(another == null) {
            return false;
        }
        if(getClass() == another.getClass()) {
            MenuItemModel anotherMenuItem = (MenuItemModel) another;
            return this.id == anotherMenuItem.getId() &&
                    this.name.equals(anotherMenuItem.getName());
        }
        if(MenuItem.class == another.getClass()) {
            MenuItem menuItem = (MenuItem) another;
            return this.id == menuItem.getId() &&
                    this.name.equals(menuItem.getName());
        }
        return false;
    }
}
