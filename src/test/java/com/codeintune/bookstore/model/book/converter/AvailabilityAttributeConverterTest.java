package com.codeintune.bookstore.model.book.converter;

import com.codeintune.bookstore.model.book.enums.Availability;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AvailabilityAttributeConverterTest {

    private AvailabilityAttributeConverter converter;

    @BeforeAll
    void setup() {
        converter = new AvailabilityAttributeConverter();
    }

    @Test
    void convertToDatabaseColumnTest() {
        Assertions.assertAll(
                () -> Assertions.assertTrue(converter.convertToDatabaseColumn(Availability.IN_STOCK)),
                () -> Assertions.assertFalse(converter.convertToDatabaseColumn(Availability.OUT_OF_STOCK)),
                () -> Assertions.assertNull(converter.convertToDatabaseColumn(null))
        );
    }

    @Test
    void convertToEntityAttributeTest() {
        Assertions.assertAll(
                () -> Assertions.assertEquals(Availability.IN_STOCK, converter.convertToEntityAttribute(Boolean.TRUE)),
                () -> Assertions.assertEquals(Availability.OUT_OF_STOCK, converter.convertToEntityAttribute(Boolean.FALSE)),
                () -> Assertions.assertNull(converter.convertToEntityAttribute(null))
        );
    }
}