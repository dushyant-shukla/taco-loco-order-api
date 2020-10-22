package com.taco.loco.service;

import com.taco.loco.domain.OrderModel;

public interface OrderService {

    public OrderModel calculateTotal(final OrderModel request) throws CloneNotSupportedException;
}
