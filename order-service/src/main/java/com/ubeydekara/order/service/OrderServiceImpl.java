package com.ubeydekara.order.service;

import com.ubeydekara.base.payload.EmailPayload;
import com.ubeydekara.base.payload.StockPayload;
import com.ubeydekara.kafka.service.ProducerService;
import com.ubeydekara.order.model.Order;
import com.ubeydekara.order.repository.OrderRepository;
import com.ubeydekara.order.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProducerService producerService;

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order create(OrderRequest orderRequest) {
        Order order = Order.builder()
                .product(orderRequest.getProduct())
                .quantity(orderRequest.getQuantity())
                .customerEmail(orderRequest.getCustomerEmail())
                .date(LocalDateTime.now())
                .build();
        order = orderRepository.saveAndFlush(order);

        this.notifyStock(order);
        this.notifyEmail(order);

        return order;
    }

    /**
     * Notify stock service for order creation
     * Updates quantity when an order is created
     *
     * @param order: the order
     */
    private void notifyStock(Order order) {
        StockPayload stockPayload = StockPayload.builder()
                .product(order.getProduct())
                .quantity(order.getQuantity())
                .build();
        producerService.sendMessage("stock", stockPayload);
    }

    /**
     * Notify email service for email sending
     * Sends email when an order is created
     *
     * @param order: the order
     */
    private void notifyEmail(Order order) {
        EmailPayload emailPayload = EmailPayload.builder()
                .emailTo(order.getCustomerEmail())
                .orderID(order.getOrderID())
                .product(order.getProduct())
                .build();
        producerService.sendMessage("email", emailPayload);
    }
}
