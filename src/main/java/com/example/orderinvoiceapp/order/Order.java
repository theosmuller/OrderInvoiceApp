package com.example.orderinvoiceapp.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.ZonedDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@Table("Sales_Order")
public class Order {
    @Id
    private Long id;
    private Long customerId;
    private ZonedDateTime orderDate;
    private String status;

    @MappedCollection(idColumn = "order_id")
    private Set<OrderLine> orderLines;
}