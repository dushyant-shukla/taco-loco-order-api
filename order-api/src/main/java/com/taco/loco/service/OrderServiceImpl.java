package com.taco.loco.service;

import com.taco.loco.domain.MenuItemModel;
import com.taco.loco.domain.OrderModel;
import com.taco.loco.entity.MenuItem;
import com.taco.loco.exception.InvalidOrderException;
import com.taco.loco.exception.MenuItemNotFoundException;
import com.taco.loco.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private MenuItemRepository itemRepository;

    @Override
    public OrderModel CalculateTotal(OrderModel request) throws CloneNotSupportedException {
        List<Long> requestedItemIds = request.getItems()
                .stream()
                .map(item -> item.getId())
                .collect(Collectors.toList());

        List<MenuItem> availableItems = (List<MenuItem>) itemRepository.findAllById(requestedItemIds);

        ValidateRequest(request, availableItems);

        double totalPrice = 0.0;
        int quantity = 0;
        for (MenuItemModel thisItem : request.getItems()) {
            Optional<MenuItem> menuItem = availableItems.stream()
                    .filter(item -> item.getId() == thisItem.getId())
                    .findFirst();

            // at this point we have already made sure that all items within the request are valid
            // this check should always return true
            if (menuItem.isPresent()) {
                totalPrice += menuItem.get().getPrice() * thisItem.getQuantity();
                quantity += thisItem.getQuantity();
            }
        }

        // calculate discount
        double discountedPrice = totalPrice;
        if (quantity >= 4) {
            discountedPrice -= 20.0 * totalPrice / 100.0;
        }

        request.setPriceBeforeDiscount(totalPrice);
        request.setPriceAfterDiscount(discountedPrice);
        return request;
    }

    @Override
    public OrderModel GetMenuItems() {
        OrderModel response = new OrderModel();
        List<MenuItemModel> menuItems = new ArrayList<>();
        for(MenuItem item : itemRepository.findAll()) {
            MenuItemModel itemModel = new MenuItemModel(item.getId(), item.getName(), item.getPrice());
            menuItems.add(itemModel);
        }
        response.setItems(menuItems);
        return response;
    }

    private void ValidateRequest(OrderModel request, List<MenuItem> availableItems) throws CloneNotSupportedException {
        OrderModel tempRequest = (OrderModel) request.clone();
        tempRequest.getItems().removeAll(availableItems);
        if (!tempRequest.getItems().isEmpty()) {
            String error = "These menu items are not available: ";
            String itemNames = tempRequest.getItems().stream()
                    .map(item -> item.getName())
                    .collect(Collectors.joining(","));
            throw new InvalidOrderException(error + itemNames);
        }
    }

    public OrderModel GetMenuItem(long id) {
        Optional<MenuItem> item = itemRepository.findById(id);
        if(item.isPresent()) {
            MenuItem menuItem = item.get();
            OrderModel response = new OrderModel();
            List<MenuItemModel> menuItems = new ArrayList<>();
            MenuItemModel itemModel = new MenuItemModel(menuItem.getId(), menuItem.getName(), menuItem.getPrice());
            menuItems.add(itemModel);
            response.setItems(menuItems);
            return response;
        }
        throw new MenuItemNotFoundException("Menu item#" + id + " does not exit.");
    }
}
