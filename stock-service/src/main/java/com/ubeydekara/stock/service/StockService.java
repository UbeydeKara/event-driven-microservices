package com.ubeydekara.stock.service;

import com.ubeydekara.basedomain.payload.StockPayload;
import com.ubeydekara.stock.model.Stock;
import com.ubeydekara.stock.response.StockResponse;

import java.util.List;

public interface StockService {

    List<Stock> getAll();

    Stock getByProduct(String product);

    StockResponse addQty(StockPayload stockPayload);

    StockResponse reduceQty(StockPayload stockPayload);
}
