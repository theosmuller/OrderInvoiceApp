package com.example.orderinvoiceapp.order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.springframework.data.relational.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDER_LINE")
public class OrderLine {
    @Id
    private Long id;

    @Column(name = "ORDER_ID")
    private Long orderId;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "UNIT_PRICE")
    private BigDecimal unitPrice;

    @Column(name = "TOTAL_PRICE")
    private BigDecimal totalPrice;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ORDER_ID", nullable = false, insertable = false, updatable = false)
//    @MapsId("orderId")
//    private SalesOrder salesOrder;
}