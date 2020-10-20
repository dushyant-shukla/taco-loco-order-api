package com.taco.loco.service;

import com.taco.loco.domain.OrderInformationModel;
import com.taco.loco.domain.OrderModel;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    public OrderInformationModel CalculateTotal(final OrderModel request);
}
