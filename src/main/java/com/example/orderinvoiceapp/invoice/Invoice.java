package com.example.orderinvoiceapp.invoice;

import com.example.orderinvoiceapp.order.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("Invoice")
public class Invoice {
    @Id
    private Long id;
    private ZonedDateTime invoiceDate;

    @MappedCollection(idColumn = "order_id")
    private Order order;

}
