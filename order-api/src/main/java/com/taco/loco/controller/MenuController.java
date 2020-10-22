package com.taco.loco.controller;

import com.taco.loco.domain.OrderModel;
import com.taco.loco.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Entry point for all menu-item related operations through the API.
 */
@RestController
@RequestMapping(path = "/api/menu")
public class MenuController {

    @Autowired
    MenuItemService menuItemService;

    @GetMapping(path = "/items", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody OrderModel getMenuItems() {
        return menuItemService.getAllMenuItems();
    }

    @GetMapping(path = "/item/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody OrderModel getMenuItem(@PathVariable(name = "id") long id) {
        return menuItemService.getMenuItem(id);
    }
}
