package com.taco.loco.controller;

import com.taco.loco.domain.OrderInformationModel;
import com.taco.loco.domain.OrderModel;
import com.taco.loco.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.taco.loco.domain.ResponseModel;

@RestController
@RequestMapping(path = "/order")
public class OrderController {

//    @Autowired
//    OrderService orderService;

    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseModel PlaceOrder(@RequestBody OrderModel request) {
        System.out.println("Request received...");
        System.out.println(request.toString());
        return new ResponseModel(1, "Order placed successfully!");
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody OrderInformationModel GetOrderDetails(@PathVariable(name = "id") long id) {
        OrderInformationModel model = new OrderInformationModel();
        model.setId(id);
        return model;
    }
}
