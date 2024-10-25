package com.example.orderinvoiceapp.invoice;

import org.mapstruct.Mapper;

@Mapper
public interface InvoiceMapper{
    Invoice map (InvoiceDTO invoiceDTO);
}
