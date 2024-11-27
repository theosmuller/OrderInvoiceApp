package com.example.orderinvoiceapp.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
//@Entity
//@Table("SALES_ORDER")
public class SalesOrder {
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Column(name = "ORDER_DATE")
    private Timestamp orderDate;

    @Column(name = "STATUS")
    private String status;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order_id")
    @JoinColumn(name = "ID", referencedColumnName = "ORDER_ID")
    private Set<OrderLine> orderLines = new HashSet<>();
}
