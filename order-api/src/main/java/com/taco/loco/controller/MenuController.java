package com.taco.loco.controller;

import com.taco.loco.domain.OrderModel;
import com.taco.loco.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/menu")
public class MenuController {

    @Autowired
    OrderService orderService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody OrderModel GetMenuItems() {
        return orderService.GetMenuItems();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody OrderModel GetMenuItem(@PathVariable(name = "id") long id) {
        return orderService.GetMenuItem(id);
    }
}
