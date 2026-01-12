package com.codeintune.bookstore.testutils.seeder.book.data;

import com.codeintune.bookstore.model.book.BookRecord;
import com.codeintune.bookstore.model.book.enums.Availability;

import java.math.BigDecimal;
import java.time.Instant;

public class BookRecordTestSeeder {

    public static BookRecord generateEntity(){
        BookRecord bookRecord = new BookRecord();
        bookRecord.setBookId(1L);
        bookRecord.setQuantity(1);
        bookRecord.setAvailability(Availability.IN_STOCK);
        bookRecord.setAuthor("Lewis Carroll");
        bookRecord.setTitle("Alice In Wonderland");
        bookRecord.setPrice(BigDecimal.ONE);
        bookRecord.setIsbn("123456789");
        bookRecord.setPublisher("Publisher");
        bookRecord.setCreatedAt(Instant.ofEpochMilli(0));
        return bookRecord;
    }

    public static BookRecord generateEntityMapped(){
        BookRecord bookRecord = generateEntity();
        bookRecord.setBookId(null);
        return bookRecord;
    }
}
