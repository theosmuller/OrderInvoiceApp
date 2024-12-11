package com.example.orderinvoiceapp.invoice;

import com.example.orderinvoiceapp.customer.CustomerValidatorService;
import com.example.orderinvoiceapp.product.ProductValidatorService;
import com.example.orderinvoiceapp.repository.blocking.InvoiceRepository;
import com.example.orderinvoiceapp.repository.blocking.OrderLineRepository;
import com.example.orderinvoiceapp.repository.blocking.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class InvoiceService {

    private final CustomerValidatorService customerValidatorService;
    private final ProductValidatorService productValidatorService;
    private final InvoiceRepository invoiceRepository;
    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;

    // For this customerId
    // Validate customer (API)
    // Get all their orders (DB)
    // For each order validate all products (API)
    // Then create invoices for all orders (DB)
    // And change their statuses (DB)

    //    This version uses Schedulers.boundedElastic() for better parallel blocking performance.
    //    Explicitly blocking at blockLast() ensures all validations complete for each order before proceeding to invoice creation,
    //    simulating the impact of blocking on performance.
    public Flux<Invoice> invoiceCustomerOrdersBlocking(Long customerId) {
        log.info("INVOICING CUSTOMER ORDERS BLOCKING");

        return customerValidatorService.validate(customerId)
                .log()
                .thenReturn(orderRepository.findSalesOrdersByCustomerId(customerId)) // Blocking repository call
                .subscribeOn(Schedulers.boundedElastic())
                .flatMapIterable(orders -> orders)
                .parallel()
                .flatMap(order -> {
                        // Blocking product validation for each order line, but done in parallel
                        return Flux.fromIterable(orderLineRepository.findByOrderId((order.getOrderId())))
                                .flatMap(productValidatorService::validateByOrderLine)
                                .next()
                                .publishOn(Schedulers.boundedElastic())
                                .thenReturn(order);
                })
                // Blocking call to save invoice
                .map(order -> {
                    Invoice invoice = invoiceRepository.save(
                            Invoice.builder().orderId(order.getOrderId()).invoiceDate(Timestamp.from(Instant.now())).build());
                    order.setStatus("INVOICED");
                    orderRepository.save(order);
                    return invoice;
                })
                .sequential();
    }


    // maybe worth doing a parallel and sequential implementation for this?

    public Flux<Invoice> invoiceCustomerOrdersBlockingSequential(Long customerId) {
        return customerValidatorService.validate(customerId)
                .thenReturn(orderRepository.findSalesOrdersByCustomerId(customerId)) // Blocking repository call
                .subscribeOn(Schedulers.boundedElastic()) // Offload blocking call
                .flatMapIterable(orders -> orders)
                .flatMap(order -> {
                        // Blocking product validation for each order line
                        return Flux.fromIterable(orderLineRepository.findByOrderId((order.getOrderId())))
                                .flatMap(productValidatorService::validateByOrderLine)
                                .next()
                                .publishOn(Schedulers.boundedElastic())
                                .thenReturn(order);
                })
                // Blocking call to save invoice, offloaded to boundedElastic scheduler
                .map(order -> {
                    Invoice invoice = invoiceRepository.save(
                            Invoice.builder().orderId(order.getOrderId()).invoiceDate(Timestamp.from(Instant.now())).build());
                    order.setStatus("INVOICED");
                    orderRepository.save(order);
                    return invoice;
                });
    }

}