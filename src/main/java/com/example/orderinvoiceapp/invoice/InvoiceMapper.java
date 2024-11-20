package com.example.orderinvoiceapp.invoice;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class InvoiceMapper{
    abstract Invoice map (InvoiceDTO invoiceDTO);
}
