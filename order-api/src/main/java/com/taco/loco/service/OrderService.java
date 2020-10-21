package com.taco.loco.service;

import com.taco.loco.domain.OrderModel;
import org.springframework.stereotype.Service;

public interface OrderService {

    public OrderModel CalculateTotal(final OrderModel request) throws CloneNotSupportedException;

    public OrderModel GetMenuItems();

    public OrderModel GetMenuItem(long id);
}
