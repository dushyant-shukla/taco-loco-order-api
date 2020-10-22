package com.taco.loco;


import com.taco.loco.domain.MenuItemModel;
import com.taco.loco.domain.OrderModel;
import com.taco.loco.entity.MenuItem;
import com.taco.loco.repository.MenuItemRepository;
import com.taco.loco.service.MenuItemServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootTest
class MenuItemServiceTests {

	@Mock
	private MenuItemRepository itemRepository;

	@InjectMocks
	private MenuItemServiceImpl menuItemService;

	private List<MenuItem> menuItems;
	private MenuItem item1;
	private MenuItem item2;

	MenuItemServiceTests() {
		menuItems = new ArrayList<>();
		item1 = new MenuItem(1, "item1", 2.5);
		item2 = new MenuItem(2, "item2", 3.5);
		menuItems.add(item1);
		menuItems.add(item2);
	}

	@DisplayName("Test get menu items.")
	@Test
	void testGetAllMenuItems() {
		Mockito.when(itemRepository.findAll()).thenReturn(menuItems);
		OrderModel result = menuItemService.getAllMenuItems();
		List<MenuItemModel> items = result.getItems();
		assert(items.size() == 2);
		assert(items.get(0).getId() == menuItems.get(0).getId());
		assert(items.get(0).getName() == menuItems.get(0).getName());
		assert(items.get(0).getPrice() == menuItems.get(0).getPrice());
		assert(items.get(1).getId() == menuItems.get(1).getId());
		assert(items.get(1).getName() == menuItems.get(1).getName());
		assert(items.get(1).getPrice() == menuItems.get(1).getPrice());
	}

	@DisplayName("Test get menu items by item-id(s)")
	@Test
	void testGetMenuItemsByItemIds() {
		Mockito.when(itemRepository.findAllById(Mockito.anyList())).thenReturn(menuItems);
		List<Long> itemIds = menuItems.stream().map(item -> item.getId()).collect(Collectors.toList());
		List<MenuItemModel> items = menuItemService.getMenuItemsByItemIds(itemIds);
		assert(items.size() == 2);
		assert(items.get(0).getId() == menuItems.get(0).getId());
		assert(items.get(0).getName() == menuItems.get(0).getName());
		assert(items.get(0).getPrice() == menuItems.get(0).getPrice());
		assert(items.get(1).getId() == menuItems.get(1).getId());
		assert(items.get(1).getName() == menuItems.get(1).getName());
		assert(items.get(1).getPrice() == menuItems.get(1).getPrice());
	}

	@DisplayName("Test get menu item by item-id")
	@Test
	void testGetMenuItemById() {
		Mockito.when(itemRepository.findById(1L)).thenReturn(Optional.of(item1));
		OrderModel result = menuItemService.getMenuItem(1L);
		List<MenuItemModel> items = result.getItems();
		assert(items.size() == 1);
		assert(items.get(0).getId() == item1.getId());
		assert(items.get(0).getName() == item1.getName());
		assert(items.get(0).getPrice() == item1.getPrice());

		Mockito.when(itemRepository.findById(2L)).thenReturn(Optional.of(item2));
		result = menuItemService.getMenuItem(2L);
		items = result.getItems();
		assert(items.size() == 1);
		assert(items.get(0).getId() == item2.getId());
		assert(items.get(0).getName() == item2.getName());
		assert(items.get(0).getPrice() == item2.getPrice());
	}
}
