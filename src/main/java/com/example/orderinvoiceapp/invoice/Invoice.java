package com.example.orderinvoiceapp.invoice;

import com.example.orderinvoiceapp.common.utils.OracleZonedDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.OffsetDateTimeSerializer;
import jakarta.persistence.Convert;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("INVOICE")
@Convert(converter=OracleZonedDateTimeSerializer.class)
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp invoiceDate;
    private Long orderId;
}
