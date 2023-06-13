package com.ubeydekara.stock.service;

import com.ubeydekara.basedomain.payload.StockPayload;
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

    @KafkaListener(topics = "order-stock", groupId = "foo")
    public StockResponse reduceQty(StockPayload stockPayload) {
        StockResponse stockResponse;
        Stock stock = stockRepository.findByProduct(stockPayload.getProduct());

        if (stock == null)
            stockResponse = StockResponse
                    .builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message("Product not found")
                    .build();

        else if (stock.getQuantity() == 0)
            stockResponse = StockResponse
                    .builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message("Out of stock!")
                    .build();

        else {
            Integer quantity = stock.getQuantity();
            stock.setQuantity(quantity - stockPayload.getQuantity());
            stockRepository.save(stock);
            stockResponse = StockResponse
                    .builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Stock is reduced. The remaining quantity: " + (stock.getQuantity() - 1))
                    .build();
        }

        log.info(stockResponse.getMessage());
        return stockResponse;
    }
}
