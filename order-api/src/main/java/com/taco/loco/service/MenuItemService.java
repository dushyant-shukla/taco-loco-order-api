package com.taco.loco.service;

import com.taco.loco.domain.MenuItemModel;
import com.taco.loco.domain.OrderModel;

import java.util.List;

public interface MenuItemService {

    public OrderModel GetMenuItems();

    public List<MenuItemModel> GetMenuItemsByItemIds(List<Long> ids);

    public OrderModel GetMenuItem(long id);
}
