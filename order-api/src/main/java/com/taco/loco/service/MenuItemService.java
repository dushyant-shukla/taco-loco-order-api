package com.taco.loco.service;

import com.taco.loco.domain.MenuItemModel;
import com.taco.loco.domain.OrderModel;

import java.util.List;

public interface MenuItemService {

    public OrderModel getAllMenuItems();

    public List<MenuItemModel> getMenuItemsByItemIds(List<Long> ids);

    public OrderModel getMenuItem(long id);
}
