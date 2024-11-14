package com.example.orderinvoiceapp.repository.blocking;

import com.example.orderinvoiceapp.invoice.Invoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long> { }
