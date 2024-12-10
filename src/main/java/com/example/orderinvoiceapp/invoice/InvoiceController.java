package com.example.orderinvoiceapp.invoice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1")
@Slf4j
public class InvoiceController {

    private final InvoiceService invoiceService;

    //POST http://localhost:8080/invoice/{customerId}
    @PostMapping("/invoice/{customerId}")
    Flux<Invoice> invoiceCustomerOrdersBlocking(@PathVariable Long customerId) {
        long startTime = System.currentTimeMillis();
        return invoiceService.invoiceCustomerOrdersBlocking(customerId).doOnComplete(() -> log.info("Time elapsed: {}\n CPU usage: {}", System.currentTimeMillis() - startTime, System.getProperties()));
    }

    @PostMapping("/invoice/{customerId}/seq")
    Flux<Invoice> invoiceCustomerOrdersBlockingSequential(@PathVariable Long customerId) {
        long startTime = System.currentTimeMillis();
        return invoiceService.invoiceCustomerOrdersBlockingSequential(customerId).doOnComplete(() -> log.info("Time elapsed: {}\n CPU usage: {}", System.currentTimeMillis() - startTime, System.getProperties()));
    }

//    @PostMapping("/invoice/{customerId}/seq/r")
//    Flux<SalesOrder> invoiceCustomerOrdersSequential(@PathVariable Long customerId) {
//        long startTime = System.currentTimeMillis();
//        return invoiceService.invoiceCustomerOrdersSequential(customerId).doOnComplete(() -> log.info("Time elapsed: {}\n CPU usage: {}", System.currentTimeMillis() - startTime, System.getProperties()));
//
//    }
}