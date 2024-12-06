package com.example.orderinvoiceapp.repository.reactive;

import com.example.orderinvoiceapp.order.OrderLine;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface ReactiveOrderLineRepository extends ReactiveCrudRepository<OrderLine, Long> {
    Flux<OrderLine> findByOrderId(Long orderId);
}
