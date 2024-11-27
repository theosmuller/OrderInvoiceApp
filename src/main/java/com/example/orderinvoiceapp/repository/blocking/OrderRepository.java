package com.example.orderinvoiceapp.repository.blocking;

import com.example.orderinvoiceapp.order.SalesOrder;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepository extends CrudRepository<SalesOrder, Long> {
//    @Query("SELECT * FROM \"SALES_ORDER\" WHERE \"CUSTOMER_ID\" = :customerId")
    List<SalesOrder> findOrdersByCustomerId(@Param("customerId") Long customerId);

    SalesOrder getOrderById(Long id);

    @Query("UPDATE SALES_ORDER SET STATUS = :newVal WHERE ID = :id RETURNING ID")
    Long updateOrderStatusByIdAndReturnId(@Param("newVal") String newVal, @Param("id") Long id);

    @Query("INSERT INTO SALES_ORDER (CUSTOMER_ID, STATUS) VALUES (10, 'PENDING')")
    SalesOrder insertMockOrder();
}

