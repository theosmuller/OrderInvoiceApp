package com.example.orderinvoiceapp.order;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderLine {
    private Long orderId;
    private Long productId;
}
