package com.ubeydekara.orderservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue
    private UUID orderID;

    @NotBlank(message = "Product is mandatory")
    private String product;

    @NotNull(message = "Quantity is mandatory")
    private Integer quantity;

    @NotBlank(message = "Customer Email is mandatory")
    private String customerEmail;

    private LocalDateTime date;
}
