//package com.example.orderinvoiceapp.repository.reactive;
//
//import com.example.orderinvoiceapp.order.SalesOrder;
//import org.springframework.data.r2dbc.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.data.repository.reactive.ReactiveCrudRepository;
//import org.springframework.stereotype.Repository;
//import reactor.core.publisher.Flux;
//
//@Repository
//public interface ReactiveOrderRepository extends ReactiveCrudRepository<SalesOrder, Long> {
//    Flux<SalesOrder> findSalesOrdersByCustomerId(Long customerId);
//
//    @Query("SELECT * FROM SALES_ORDER WHERE CUSTOMER_ID = 1")
//    Flux<SalesOrder> findMockSalesOrders();
//}
