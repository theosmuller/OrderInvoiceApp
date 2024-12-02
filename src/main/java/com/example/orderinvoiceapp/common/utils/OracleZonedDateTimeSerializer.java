package com.example.orderinvoiceapp.common.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

@Converter(autoApply = true)
public class OracleZonedDateTimeSerializer implements AttributeConverter<ZonedDateTime, Date> {


    @Override
    public Date convertToDatabaseColumn(ZonedDateTime attribute) {
        return attribute == null ? null : java.util.Date.from(attribute.withZoneSameInstant
                (ZoneOffset.UTC).toInstant());
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(Date dbData) {
        return dbData == null ? null : ZonedDateTime.ofInstant(dbData.toInstant(), ZoneId.of("EST"));
    }
}