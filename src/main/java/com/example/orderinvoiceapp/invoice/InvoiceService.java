package com.example.orderinvoiceapp.invoice;

import com.example.orderinvoiceapp.customer.CustomerValidatorService;
import com.example.orderinvoiceapp.order.Order;
import com.example.orderinvoiceapp.order.OrderLine;
import com.example.orderinvoiceapp.order.OrderRepository;
import com.example.orderinvoiceapp.product.ProductValidatorService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper mapper;
    private final OrderRepository orderRepository;
    private final CustomerValidatorService customerValidatorService;
    private final ProductValidatorService productValidatorService;

    // For this customerId
    // Validate customer
    // Get all their orders
    // For each order validate all products
    // Then create invoices for all orders
    // And change their statuses

    // maybe worth doing a parallel and sequential implementation for this?

    public Flux<Invoice> invoiceCustomerOrdersBlocking(Long customerId) {
        return customerValidatorService.validate(customerId)
                .thenReturn(orderRepository.getOrdersByCustomerId(customerId))
                .flatMapIterable(orders -> orders)
                .parallel()
                .doOnEach(order -> Flux.fromIterable(Objects.requireNonNull(order.get())
                        .getOrderLines())
                        .parallel()
                        .doOnEach(productValidatorService::validateByOrderLine))
                .map(order -> invoiceRepository.save(mapper.map(InvoiceDTO.builder().order(order).invoiceDate(ZonedDateTime.now()).build())))
                .sequential();
    }

}