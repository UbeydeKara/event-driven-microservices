package com.ubeydekara.orderservice.request;

import lombok.Data;

@Data
public class OrderRequest {
    private String product;
    private Integer quantity;
    private String customerEmail;
}
