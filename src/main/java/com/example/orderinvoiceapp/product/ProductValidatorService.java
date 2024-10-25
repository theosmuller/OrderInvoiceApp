package com.example.orderinvoiceapp.product;

import com.example.orderinvoiceapp.order.Order;
import com.example.orderinvoiceapp.order.OrderLine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;

import java.util.Objects;

import static com.example.orderinvoiceapp.product.ProductTypeEnum.DECOMISSIONED;

@Service
@RequiredArgsConstructor
public class ProductValidatorService {
    private final ProductClient productClient;

    public Mono<Boolean> validateByOrderLine(Signal<OrderLine> orderLine) {
        return productClient.getProductById(Objects.requireNonNull(orderLine.get()).getProductId())
                .map(productDTO -> !DECOMISSIONED.equals(productDTO.getProductType()) && !productDTO.getPrice().isNaN())
                .flatMap(isValid -> !isValid ? Mono.error(new RuntimeException("Product is invalid")) : Mono.just(Boolean.TRUE));
    }
}
