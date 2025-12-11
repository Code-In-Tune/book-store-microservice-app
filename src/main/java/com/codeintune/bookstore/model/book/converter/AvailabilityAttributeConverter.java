package com.codeintune.bookstore.model.book.converter;

import com.codeintune.bookstore.model.book.enums.Availability;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class AvailabilityAttributeConverter implements AttributeConverter<Availability, Boolean> {

    @Override
    public Boolean convertToDatabaseColumn(Availability availability) {
        if(availability == null){
            return null;
        }
        return Availability.IN_STOCK.equals(availability);
    }

    @Override
    public Availability convertToEntityAttribute(Boolean s) {
        if(s == null){
            return null;
        }
        return s ? Availability.IN_STOCK : Availability.OUT_OF_STOCK;
    }
}
