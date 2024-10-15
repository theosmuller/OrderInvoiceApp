package com.example.orderinvoiceapp.invoice;

import com.example.orderinvoiceapp.customer.CustomerValidatorService;
import com.example.orderinvoiceapp.order.OrderRepository;
import com.example.orderinvoiceapp.product.ProductValidatorService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
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

    public Flux<InvoiceDTO> invoiceCustomerOrdersBlocking(Long customerId) {
        return customerValidatorService.validate(customerId)
                .thenReturn(orderRepository.getOrdersByCustomerId(customerId))
                .flatMapIterable(orders -> orders)
                .flatMap(order -> order
                        .getOrderLines()
                        .parallelStream()
                        .filter(orderLine -> productValidatorService.validate(orderLine.getProductId()))
                        .forEach(orderLine -> productValidatorService.validate(orderLine.getProductId()));
    }

}