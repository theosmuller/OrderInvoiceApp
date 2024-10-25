package com.example.orderinvoiceapp.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;
@Entit
@Data
@AllArgsConstructor
public class Order {
    private @Id int id;
    private Long customerId;
    @OneToMany
    private List<OrderLine> orderLines;
}
