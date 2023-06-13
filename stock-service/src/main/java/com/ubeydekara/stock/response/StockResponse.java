package com.ubeydekara.stock.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class StockResponse {
    private HttpStatus httpStatus;
    private String message;
}
