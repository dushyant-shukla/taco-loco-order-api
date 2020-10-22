package com.taco.loco;

import com.taco.loco.domain.MenuItemModel;
import com.taco.loco.domain.OrderModel;
import com.taco.loco.exception.InvalidOrderException;
import com.taco.loco.service.MenuItemService;
import com.taco.loco.service.OrderServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class OrderServiceTests {

    @Mock
    private MenuItemService menuItemService;

    @InjectMocks
    private OrderServiceImpl orderService;

    private OrderModel orderRequestWithDiscount;
    private OrderModel orderRequestWithoutDiscount;
    private OrderModel invalidOrderRequestWithException;
    private List<Long> itemIdsWithDiscount;
    private List<Long> itemIdsWithoutDiscount;
    private List<MenuItemModel> itemsWithDiscount;
    private List<MenuItemModel> itemsWithoutDiscount;
    private List<MenuItemModel> itemListWithInvalidItems;
    private MenuItemModel item1;
    private MenuItemModel item2;
    private MenuItemModel item3;
    private MenuItemModel item4;
    private MenuItemModel item5;
    OrderServiceTests() {
        itemIdsWithDiscount = new ArrayList<>();
        itemIdsWithDiscount.add(1L);
        itemIdsWithDiscount.add(2L);
        itemIdsWithDiscount.add(3L);
        item1 = new MenuItemModel(1L, "item1", 2.5, 2);
        item2 = new MenuItemModel(2L, "item2", 3.5, 4);
        item3 = new MenuItemModel(3L, "item3", 4.5, 2);
        itemsWithDiscount = new ArrayList<>();
        itemsWithDiscount.add(item1);
        itemsWithDiscount.add(item2);
        itemsWithDiscount.add(item3);
        orderRequestWithDiscount = new OrderModel();
        orderRequestWithDiscount.setItems(itemsWithDiscount);

        itemIdsWithoutDiscount = new ArrayList<>();
        itemIdsWithoutDiscount.add(4L);
        itemIdsWithoutDiscount.add(5L);
        item4 = new MenuItemModel(4L, "item4", 2.5, 1);
        item5 = new MenuItemModel(5L, "item5", 3.5, 1);
        itemsWithoutDiscount = new ArrayList<>();
        itemsWithoutDiscount.add(item4);
        itemsWithoutDiscount.add(item5);
        orderRequestWithoutDiscount = new OrderModel();
        orderRequestWithoutDiscount.setItems(itemsWithoutDiscount);

        itemListWithInvalidItems = new ArrayList<>();
        itemListWithInvalidItems.add(item1);
        itemListWithInvalidItems.add(item2);
        itemListWithInvalidItems.add(item3);
        itemListWithInvalidItems.add(item4);
        invalidOrderRequestWithException = new OrderModel();
        invalidOrderRequestWithException.setItems(itemListWithInvalidItems);

    }

    @DisplayName("Test calculating total with discount.")
    @Test
    void testCalculateTotalWithDiscount() throws CloneNotSupportedException {
        Mockito.when(menuItemService.getMenuItemsByItemIds(itemIdsWithDiscount)).thenReturn(itemsWithDiscount);
        OrderModel result = orderService.calculateTotal(orderRequestWithDiscount);
        List<MenuItemModel> menuItems = result.getItems();
        assert(menuItems.size() == 3);
        assert(result.getPriceBeforeDiscount() == 28.0);
        assert(result.getPriceAfterDiscount() == 22.4);
    }

    @DisplayName("Test calculating total without discount.")
    @Test
    void testCalculateTotalWithoutDiscount() throws CloneNotSupportedException {
        Mockito.when(menuItemService.getMenuItemsByItemIds(itemIdsWithoutDiscount)).thenReturn(itemsWithoutDiscount);
        OrderModel result = orderService.calculateTotal(orderRequestWithoutDiscount);
        List<MenuItemModel> menuItems = result.getItems();
        assert(menuItems.size() == 2);
        assert(result.getPriceBeforeDiscount() == 6.0);
        assert(result.getPriceAfterDiscount() == 6.0);
    }

    @DisplayName("Test calculating total with invalid request")
    @Test
    void testCalculateTotalWithInvalidRequestException() throws CloneNotSupportedException {
        Mockito.when(menuItemService.getMenuItemsByItemIds(Mockito.anyList())).thenReturn(itemsWithDiscount);
        Exception exception = Assertions.assertThrows(InvalidOrderException.class, ()->{
            orderService.calculateTotal(invalidOrderRequestWithException);
        });
        assert(exception.getMessage().equals("These menu items are not available: item4"));
    }
}
