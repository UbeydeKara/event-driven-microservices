package com.ubeydekara.order.controller;

import com.ubeydekara.base.response.ResponseHandler;
import com.ubeydekara.order.model.Order;
import com.ubeydekara.order.request.OrderRequest;
import com.ubeydekara.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<Object> getAll() {
        List<Order> orderList = orderService.getAll();
       return ResponseHandler.generateResponse(HttpStatus.OK, orderList);
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody OrderRequest orderRequest) {
        try {
            Order newOrder = orderService.create(orderRequest);
            return ResponseHandler.generateResponse(HttpStatus.OK, newOrder, "Email just sent.");
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
