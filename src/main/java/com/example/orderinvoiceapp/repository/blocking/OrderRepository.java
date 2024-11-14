package com.example.orderinvoiceapp.repository.blocking;

import com.example.orderinvoiceapp.order.Order;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    // get all order requests by customer ID
    List<Order> getOrdersByCustomerId(Long customerId);

    // Update order status by ID
    @Query(value = "UPDATE \"Order\" SET status = :newVal WHERE id = :id RETURNING id")
    Long updateOrderStatusByIdAndReturnId(@Param("newVal") String newVal, @Param("id") Long id);

}
