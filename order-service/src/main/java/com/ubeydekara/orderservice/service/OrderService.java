package com.ubeydekara.orderservice.service;

import com.ubeydekara.orderservice.model.Order;
import com.ubeydekara.orderservice.request.OrderRequest;

import java.util.List;

public interface OrderService {
    List<Order> getAll();
    Order create(OrderRequest orderRequest);

}