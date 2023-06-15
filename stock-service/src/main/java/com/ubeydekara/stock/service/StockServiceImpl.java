package com.ubeydekara.stock.service;

import com.ubeydekara.base.payload.StockPayload;
import com.ubeydekara.stock.mapper.StockMapper;
import com.ubeydekara.stock.model.Stock;
import com.ubeydekara.stock.repository.StockRepository;
import com.ubeydekara.stock.response.StockResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    public List<Stock> getAll() {
        return stockRepository.findAll();
    }

    public Stock getByProduct(String product) {
        return stockRepository.findByProduct(product);
    }

    public StockResponse addQty(StockPayload stockPayload) {
        stockRepository.save(stockMapper.payloadToStock(stockPayload));
        return StockResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message("Stock added.")
                .build();
    }

    @KafkaListener(topics = "stock", groupId = "order")
    public StockResponse reduceQty(StockPayload stockPayload) {
        StockResponse stockResponse;
        Stock stock = stockRepository.findByProduct(stockPayload.getProduct());

        // add mock quantity if stock not exists
        if (stock == null) {
            stock = Stock.builder()
                    .product(stockPayload.getProduct())
                    .quantity(1000)
                    .build();
        }

        if (stock.getQuantity() < stockPayload.getQuantity())
            stockResponse = StockResponse
                    .builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message("Out of stock!")
                    .build();

        else {
            stock.setQuantity(stock.getQuantity() - stockPayload.getQuantity());
            stock = stockRepository.saveAndFlush(stock);
            stockResponse = StockResponse
                    .builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Stock is reduced. The remaining quantity: " + stock.getQuantity())
                    .build();
        }

        log.info(stockResponse.getMessage());
        return stockResponse;
    }
}
