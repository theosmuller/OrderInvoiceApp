package com.example.orderinvoiceapp.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.example.orderinvoiceapp.product.ProductTypeEnum.DECOMISSIONED;

@Service
@RequiredArgsConstructor
public class ProductValidatorService {
    private final ProductClient productClient;

    public Mono<Boolean> validate(Long productId) {
        return productClient.getProductById(productId)
                .map(productDTO -> !DECOMISSIONED.equals(productDTO.getProductType()) && !productDTO.getPrice().isNaN());
    }
}
