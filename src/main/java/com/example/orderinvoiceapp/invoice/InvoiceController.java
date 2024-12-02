package com.example.orderinvoiceapp.invoice;

import com.example.orderinvoiceapp.order.SalesOrder;
import com.example.orderinvoiceapp.repository.blocking.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1")
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final OrderRepository orderRepository;

    //POST http://localhost:8080/invoice/{customerId}
    @PostMapping("/invoice/{customerId}")
    Flux<Invoice> invoiceCustomerOrdersBlocking(@PathVariable Long customerId) {
        return invoiceService.invoiceCustomerOrdersBlocking(customerId);
    }
    //TEST CONTROLLER TO GET DB TO WORK
    @PostMapping()
    SalesOrder insertMockOrder() {
        return orderRepository.getSalesOrderByOrderId(1L);
    }
}