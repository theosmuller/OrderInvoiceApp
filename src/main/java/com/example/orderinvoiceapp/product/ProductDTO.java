package com.example.orderinvoiceapp.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private String sku;
    private String name;
    private String description;
    private Float price;
    private ProductTypeEnum productType;

}
