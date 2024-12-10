package com.example.orderinvoiceapp.product;

import com.example.orderinvoiceapp.order.OrderLine;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;

import java.util.Objects;

import static com.example.orderinvoiceapp.product.ProductTypeEnum.DECOMISSIONED;

@Service
@AllArgsConstructor
@Slf4j
public class ProductValidatorService {
    private final ProductClient productClient;

    public Mono<Boolean> validateByOrderLine(OrderLine orderLine) {
        log.info("Validating order line={}", orderLine);
        return productClient.getProductById(Objects.requireNonNull(orderLine).getProductId())
                .map(productDTO -> !DECOMISSIONED.equals(productDTO.getProductType()) && !productDTO.getPrice().isNaN())
                .flatMap(isValid -> !isValid ? Mono.error(new RuntimeException("Product is invalid")) : Mono.just(Boolean.TRUE));
    }
}
