package com.ubeydekara.stock.controller;

import com.ubeydekara.base.payload.StockPayload;
import com.ubeydekara.base.response.ResponseHandler;
import com.ubeydekara.stock.model.Stock;
import com.ubeydekara.stock.response.StockResponse;
import com.ubeydekara.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stock")
@Slf4j
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping
    public ResponseEntity<Object> getAll() {
        List<Stock> stockList = stockService.getAll();
        return ResponseHandler.generateResponse(HttpStatus.OK, stockList);
    }

    @GetMapping("/{product}")
    public ResponseEntity<Object> getByProduct(@PathVariable("product") String product) {
        Stock stock = stockService.getByProduct(product);
        return ResponseHandler.generateResponse(HttpStatus.OK, stock);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addQty(@RequestBody StockPayload stockPayload) {
        StockResponse stockResponse = stockService.addQty(stockPayload);
        return ResponseHandler.generateResponse(stockResponse.getHttpStatus(), stockResponse.getMessage());
    }

    @PostMapping("/reduce")
    public ResponseEntity<Object> reduceQty(@RequestBody StockPayload stockPayload) {
        StockResponse stockResponse = stockService.reduceQty(stockPayload);
        return ResponseHandler.generateResponse(stockResponse.getHttpStatus(), stockResponse.getMessage());
    }

}
