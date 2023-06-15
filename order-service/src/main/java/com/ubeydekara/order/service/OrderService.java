package com.ubeydekara.order.service;

import com.ubeydekara.order.model.Order;
import com.ubeydekara.order.request.OrderRequest;

import java.util.List;

public interface OrderService {
    List<Order> getAll();
    Order create(OrderRequest orderRequest);

}