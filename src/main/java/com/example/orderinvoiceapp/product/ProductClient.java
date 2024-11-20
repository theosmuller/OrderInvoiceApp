package com.example.orderinvoiceapp.product;

import com.example.orderinvoiceapp.customer.CustomerDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.example.orderinvoiceapp.product.ProductTypeEnum.APPLIANCE;
@Service
public class ProductClient {
    //get product data by product ID
    public Mono<ProductDTO> getProductById(Long productId) {
        return Mono.delay(Duration.ofMillis(10))
                .thenReturn(ProductDTO.builder()
                        .sku("123-ABCD")
                        .name("Fritadeira Airfryer Essential XL Digital Philips Walita Preta 2000W - RI9270")
                        .description("A única com Tecnologia RapidAir patenteada que garante o fluxo de ar por todos os lados, o cozimento mais rápido e uniforme, deixando os alimentos macios por dentro e crocantes por fora. São 2000W e capacidade total de 6,2l e útil de 1,2kg. Com ajuste de temperatura de 80 a 200 graus, timer de 60 minutos e desligamento automático. Display Digital. Fácil de lavar: peças removíveis e preparadas para a máquina de lavar. Inspire-se com + de 500 receitas no App NutriU")
                        .price(744.99F)
                        .productType(APPLIANCE)
                        .build());
    }
}
