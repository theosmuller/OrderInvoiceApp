package com.example.orderinvoiceapp.repository.reactive;

import com.example.orderinvoiceapp.invoice.Invoice;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactiveInvoiceRepository extends ReactiveCrudRepository<Invoice, Long> {
}
