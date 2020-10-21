package com.taco.loco.controller;

import com.taco.loco.domain.OrderModel;
import com.taco.loco.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Entry point for all order related operations through the API.
 */
@RestController
@RequestMapping(path = "/api/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody OrderModel CalculateTotal(@RequestBody @Validated OrderModel request) throws CloneNotSupportedException {
        return orderService.CalculateTotal(request);
    }
}
