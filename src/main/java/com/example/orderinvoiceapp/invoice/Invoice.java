package com.example.orderinvoiceapp.invoice;

import com.example.orderinvoiceapp.order.Order;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class Invoice {
    private Order order;
    private ZonedDateTime invoiceDate;
}
