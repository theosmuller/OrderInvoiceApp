package com.example.orderinvoiceapp.invoice;

import com.example.orderinvoiceapp.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import reactor.core.publisher.Flux;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    //POST http://localhost:8080/invoice/{customerId}
    @PostMapping("/invoice/{customerId}")
    Flux<InvoiceDTO> invoiceCustomerOrdersBlocking(@PathVariable Long customerId){
        return invoiceService.invoiceCustomerOrdersBlocking(customerId);
    }
    //
}