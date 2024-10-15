package com.example.orderinvoiceapp.invoice;

import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository {
    // insert invoiced order with payment date
    // table contains: order ID, customer ID, payment date
    // could be an api
}
