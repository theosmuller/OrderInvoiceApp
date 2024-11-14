package com.example.orderinvoiceapp.invoice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@Controller
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    //POST http://localhost:8080/invoice/{customerId}
    @PostMapping("/invoice/{customerId}")
    Flux<Invoice> invoiceCustomerOrdersBlocking(@PathVariable Long customerId){
        return invoiceService.invoiceCustomerOrdersBlocking(customerId);
    }
    //
}