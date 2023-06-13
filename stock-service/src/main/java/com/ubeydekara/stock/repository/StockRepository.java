package com.ubeydekara.stock.repository;

import com.ubeydekara.stock.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StockRepository extends JpaRepository<Stock, UUID> {
    Stock findByProduct(String product);
}
