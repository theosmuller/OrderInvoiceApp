package com.example.orderinvoiceapp.invoice;

import com.example.orderinvoiceapp.customer.CustomerValidatorService;
import com.example.orderinvoiceapp.repository.blocking.InvoiceRepository;
import com.example.orderinvoiceapp.repository.blocking.OrderRepository;
import com.example.orderinvoiceapp.product.ProductValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.mapping.JdbcMappingContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.ZonedDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    @Autowired
    private JdbcMappingContext jdbcMappingContext;
    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper mapper;
    private final OrderRepository orderRepository;
    private final CustomerValidatorService customerValidatorService;
    private final ProductValidatorService productValidatorService;

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
        return customerValidatorService.validate(customerId)
                .thenReturn(orderRepository.getOrdersByCustomerId(customerId)) // Blocking repository call
                .subscribeOn(Schedulers.boundedElastic())
                .flatMapIterable(orders -> orders)
                .parallel()
                .runOn(Schedulers.boundedElastic()) // Explicitly using bounded elastic for blocking operations
                .doOnEach(order -> {
                    // Blocking product validation for each order line, but done in parallel
                    Flux.fromIterable(Objects.requireNonNull(order.get()).getOrderLines())
                            .doOnEach(productValidatorService::validateByOrderLine)
                            .blockLast(); // Ensures validation completes before proceeding
                })
                // Blocking call to save invoice
                .map(order -> {
                    Invoice invoice = invoiceRepository.save(
                            mapper.map(InvoiceDTO.builder().order(order).invoiceDate(ZonedDateTime.now()).build()));
                    orderRepository.updateOrderStatusByIdAndReturnId("INVOICED", order.getId());
                    return invoice;
                })
                .sequential();
    }

    // maybe worth doing a parallel and sequential implementation for this?

    public Flux<Invoice> invoiceCustomerOrdersBlockingSequential(Long customerId) {
        return customerValidatorService.validate(customerId)
                .thenReturn(orderRepository.getOrdersByCustomerId(customerId)) // Blocking repository call
                .subscribeOn(Schedulers.boundedElastic()) // Offload blocking call
                .flatMapIterable(orders -> orders)
                .doOnEach(order -> {
                    // Blocking product validation for each order line
                    Flux.fromIterable(Objects.requireNonNull(order.get()).getOrderLines())
                            .doOnEach(productValidatorService::validateByOrderLine)
                            .blockLast(); // Ensures validation completes before proceeding
                })
                // Blocking call to save invoice, offloaded to boundedElastic scheduler
                .map(order -> {
                    Invoice invoice = invoiceRepository.save(
                            mapper.map(InvoiceDTO.builder().order(order).invoiceDate(ZonedDateTime.now()).build()));
                    orderRepository.updateOrderStatusByIdAndReturnId("INVOICED", order.getId());
                    return invoice;
                })
                .subscribeOn(Schedulers.boundedElastic());
    }
}