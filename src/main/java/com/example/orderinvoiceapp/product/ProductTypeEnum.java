package com.example.orderinvoiceapp.product;

public enum ProductTypeEnum {
    APPLIANCE(1L),
    SILVERWARE(2L),
    DECORATION(3L),
    FURNITURE(4L),
    DECOMISSIONED(-1L);

    private final Long code;

    ProductTypeEnum(Long code) {
        this.code = code;
    }

    public Long getCode() {
        return code;
    }

}
