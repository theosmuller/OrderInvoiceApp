package com.example.orderinvoiceapp.order;

import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ReactiveOrderRepository extends R2dbcRepository<Order, Long> {
    @Query("SELECT * FROM \"Order\" WHERE customer_id = :customerId")
    Flux<Order> getOrdersByCustomerId(Long customerId);

    // Update order status by ID (Reactive)
    @Modifying
    @Query("UPDATE \"Order\" SET status = :newVal WHERE id = :id RETURNING id")
    Mono<Long> updateOrderStatusByIdAndReturnId(@Param("newVal") String newVal, @Param("id") Long id);
}
