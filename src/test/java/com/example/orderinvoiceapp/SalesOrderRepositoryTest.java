package com.example.orderinvoiceapp;


import com.example.orderinvoiceapp.order.SalesOrder;
import com.example.orderinvoiceapp.repository.blocking.OrderRepository;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
@AllArgsConstructor
public class SalesOrderRepositoryTest {

    private final OrderRepository orderRepository;

    @Test
    public void testGetOrderById() {
        Object salesOrder = orderRepository.getSalesOrderByOrderId(1L);
        assertNotNull(salesOrder);
    }

    @Test
    public void testFindSalesOrdersByCustomerId() {
        List<SalesOrder> salesOrders = orderRepository.findSalesOrdersByCustomerId(2L);
        assertNotNull(salesOrders);
        assertFalse(salesOrders.isEmpty());
        salesOrders.forEach(System.out::println);
    }

    @Test
    public void testInsertMockOrder() {
        orderRepository.insertMockOrder();
    }
}