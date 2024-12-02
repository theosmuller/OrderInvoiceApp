package com.example.orderinvoiceapp.order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
@Entity
@Table("SALES_ORDER")
public class SalesOrder {
    @Id
    @Column(name = "ORDER_ID")
    private Long orderId;

    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Column(name = "ORDER_DATE")
    private Timestamp orderDate;

    @Column(name = "STATUS")
    private String status;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "salesOrder", cascade = CascadeType.ALL)
//    @JoinColumn(name = "ID", referencedColumnName = "ORDER_ID")
//    private Set<OrderLine> orderLines = new HashSet<>(0);
}
