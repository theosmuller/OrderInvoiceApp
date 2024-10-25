package com.example.orderinvoiceapp.invoice;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
    // insert invoiced order with payment date
    // table contains: order ID, customer ID, payment date
    // could be an api

}
