package com.ubeydekara.base.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailPayload {
    private UUID orderID;
    private String product;
    private String emailTo;
}
