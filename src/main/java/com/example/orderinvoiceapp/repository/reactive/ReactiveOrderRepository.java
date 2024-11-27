//package com.example.orderinvoiceapp.repository.reactive;
//
//import com.example.orderinvoiceapp.order.SalesOrder;
//import org.springframework.data.jdbc.repository.query.Modifying;
//import org.springframework.data.jdbc.repository.query.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.data.repository.reactive.ReactiveCrudRepository;
//import org.springframework.stereotype.Repository;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//@Repository
//public interface ReactiveOrderRepository extends ReactiveCrudRepository<SalesOrder, Long> {
//    @Query("SELECT * FROM \"Order\" WHERE customer_id = :customerId")
//    Flux<SalesOrder> getOrdersByCustomerId(Long customerId);
//
//    // Update order status by ID (Reactive)
//    @Modifying
//    @Query("UPDATE \"Order\" SET status = :newVal WHERE id = :id RETURNING id")
//    Mono<Long> updateOrderStatusByIdAndReturnId(@Param("newVal") String newVal, @Param("id") Long id);
//}
