package com.example.orderinvoiceapp.order;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    // get all order requests by customer ID
    List<Order> getOrdersByCustomerId(Long customerId);
    // update order status after invoice
    @Query(value = "UPDATE Order SET status = :newVal WHERE id = :id RETURNING id")
    Long updateOrderStatusByIdAndReturnId(@Param("newVal") String newVal, @Param("id") Long id);

}
