package com.example.orderinvoiceapp.invoice;

import com.example.orderinvoiceapp.order.SalesOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {
    private SalesOrder salesOrder;
    private ZonedDateTime invoiceDate;
}
