package com.example.orderinvoiceapp.repository.blocking;

import com.example.orderinvoiceapp.order.OrderLine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineRepository extends CrudRepository<OrderLine, Long> {
    List<OrderLine> findByOrderId(Long orderId);
}
