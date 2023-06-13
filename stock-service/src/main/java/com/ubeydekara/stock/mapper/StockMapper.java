package com.ubeydekara.stock.mapper;

import com.ubeydekara.basedomain.payload.StockPayload;
import com.ubeydekara.stock.model.Stock;
import org.springframework.stereotype.Component;

@Component
public class StockMapper {
    public Stock payloadToStock(StockPayload stockPayload) {
        return Stock.builder()
                .product(stockPayload.getProduct())
                .quantity(stockPayload.getQuantity())
                .build();
    }
}
