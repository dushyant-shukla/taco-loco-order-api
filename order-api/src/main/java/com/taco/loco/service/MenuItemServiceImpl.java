package com.taco.loco.service;

import com.taco.loco.domain.MenuItemModel;
import com.taco.loco.domain.OrderModel;
import com.taco.loco.entity.MenuItem;
import com.taco.loco.exception.MenuItemNotFoundException;
import com.taco.loco.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class holds menu-items' related operations
 */
@Service
public class MenuItemServiceImpl implements MenuItemService {

    @Autowired
    private MenuItemRepository itemRepository;

    /**
     *
     * @return {@link OrderModel} details of all
     */
    @Override
    public OrderModel getAllMenuItems() {
        OrderModel response = new OrderModel();
        List<MenuItem> availableItems = (List<MenuItem>) itemRepository.findAll();
        List<MenuItemModel> menuItems = availableItems.stream()
                .map(item -> new MenuItemModel(item.getId(), item.getName(), item.getPrice()))
                .collect(Collectors.toList());
        response.setItems(menuItems);
        return response;
    }

    /**
     * This method returns the details of menu-item(s) requested though their associated id(s).
     *
     * @param ids a list of menu-items' id(s)
     * @return List of {@link MenuItemModel} containing details of the requested menu-item(s)
     */
    @Override
    public List<MenuItemModel> getMenuItemsByItemIds(List<Long> ids) {
        List<MenuItem> availableItems = (List<MenuItem>) itemRepository.findAllById(ids);
        return availableItems.stream()
                .map(item -> new MenuItemModel(item.getId(), item.getName(), item.getPrice()))
                .collect(Collectors.toList());
    }

    /**
     * This method returns the details of a menu-item requested through
     * its associated id.
     *
     * @param id an id associated with a menu item in the database
     * @return {@link OrderModel} details of the menu-item requested
     */
    @Override
    public OrderModel getMenuItem(long id) {
        Optional<MenuItem> item = itemRepository.findById(id);
        if(item.isPresent()) {
            MenuItem menuItem = item.get();
            OrderModel response = new OrderModel();
            List<MenuItemModel> menuItems = new ArrayList<>();
            menuItems.add(new MenuItemModel(menuItem.getId(), menuItem.getName(), menuItem.getPrice()));
            response.setItems(menuItems);
            return response;
        }
        throw new MenuItemNotFoundException("Menu item#" + id + " does not exit.");
    }
}
