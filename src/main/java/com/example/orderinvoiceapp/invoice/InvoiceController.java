package com.example.orderinvoiceapp.invoice;

import com.example.orderinvoiceapp.order.SalesOrder;
import com.example.orderinvoiceapp.repository.blocking.OrderRepository;
import com.example.orderinvoiceapp.repository.reactive.ReactiveOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1")
@Slf4j
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final ReactiveOrderRepository reactiveOrderRepository;

    //POST http://localhost:8080/invoice/{customerId}
//    @PostMapping("/invoice/{customerId}")
//    Flux<Invoice> invoiceCustomerOrdersBlocking(@PathVariable Long customerId) {
//        long startTime = System.currentTimeMillis();
//        return invoiceService.invoiceCustomerOrdersBlocking(customerId).doOnComplete(() -> log.info("Time elapsed: {}\n CPU usage: {}", System.currentTimeMillis() - startTime, System.getProperties()));
//    }
//
//    @PostMapping("/invoice/{customerId}/seq")
//    Flux<Invoice> invoiceCustomerOrdersBlockingSequential(@PathVariable Long customerId) {
//        long startTime = System.currentTimeMillis();
//        return invoiceService.invoiceCustomerOrdersBlockingSequential(customerId).doOnComplete(() -> log.info("Time elapsed: {}\n CPU usage: {}", System.currentTimeMillis() - startTime, System.getProperties()));
//    }

    @PostMapping("/invoice/{customerId}/seq/r")
    Flux<SalesOrder> invoiceCustomerOrdersSequential(@PathVariable Long customerId) {
        long startTime = System.currentTimeMillis();
        return invoiceService.invoiceCustomerOrdersSequential(customerId).doOnComplete(() -> log.info("Time elapsed: {}\n CPU usage: {}", System.currentTimeMillis() - startTime, System.getProperties()));

    }

    @GetMapping()
    Flux<SalesOrder> test() {
        return reactiveOrderRepository.findSalesOrdersByCustomerId(1L);
    }
}