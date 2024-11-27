package com.example.orderinvoiceapp.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor

@Table(name = "ORDERLINE")
public class OrderLine {
    @Id
    private Long id;

//    @Column(name = "ORDER_ID")
//    private Long orderId;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "UNIT_PRICE")
    private BigDecimal unitPrice;

    @Column(name = "TOTAL_PRICE")
    private BigDecimal totalPrice;

    @ManyToOne()
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID")
    private SalesOrder salesOrder;
}