package com.taco.loco.service;

import com.taco.loco.domain.MenuItemModel;
import com.taco.loco.domain.OrderModel;
import com.taco.loco.exception.InvalidOrderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class holds functionality related to order-processing.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private MenuItemService menuItemService;

    /**
     * This method calculates total price for the items received in the request.
     * It also calculates the discount on the total price when applicable.
     * @param {@link OrderModel} containing a list of menu items along with requested quantities
     * @return {@link OrderModel} containing a list of items along with total price and price after discount
     * @throws CloneNotSupportedException
     */
    @Override
    public OrderModel CalculateTotal(OrderModel request) throws CloneNotSupportedException {
        List<Long> requestedItemIds = request.getItems()
                .stream()
                .map(item -> item.getId())
                .collect(Collectors.toList());

        // get all the menu items from database
        List<MenuItemModel> availableItems = (List<MenuItemModel>) menuItemService.GetMenuItemsByItemIds(requestedItemIds);

        // validate menu items received in request against items from database
        ValidateRequest(request, availableItems);

        double totalPrice = 0.0;
        int quantity = 0;
        for (MenuItemModel thisItem : request.getItems()) {
            Optional<MenuItemModel> menuItem = availableItems.stream()
                    .filter(item -> item.getId() == thisItem.getId())
                    .findFirst();

            // at this point we have already made sure that all the menu items within the request are valid
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

    /**
     * This method tests validity of the menu items received in the request.
     * @throws CloneNotSupportedException if request received is not valid.
     * @returns void if the items in the request are valid
     */
    private void ValidateRequest(OrderModel request,  List<MenuItemModel> availableItems) throws CloneNotSupportedException {
        OrderModel tempRequest = (OrderModel) request.clone();
        tempRequest.getItems().removeAll(availableItems);  // if all items in the request are valid, then tempRequest's item-list must be empty after this
        if (!tempRequest.getItems().isEmpty()) {
            String error = "These menu items are not available: ";
            String itemNames = tempRequest.getItems().stream()
                    .map(item -> item.getName())
                    .collect(Collectors.joining(","));
            throw new InvalidOrderException(error + itemNames);
        }
    }
}
