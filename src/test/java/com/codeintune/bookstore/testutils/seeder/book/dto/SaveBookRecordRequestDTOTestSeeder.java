package com.codeintune.bookstore.testutils.seeder.book.dto;

import com.codeintune.bookstore.dto.book.request.SaveBookRecordRequestDTO;

import java.math.BigDecimal;

public class SaveBookRecordRequestDTOTestSeeder {


    public static SaveBookRecordRequestDTO saveBookRecordRequestDTO(){
        SaveBookRecordRequestDTO bookRecord = new SaveBookRecordRequestDTO();
        bookRecord.setQuantity(1);
        bookRecord.setAuthor("Lewis Carroll");
        bookRecord.setTitle("Alice In Wonderland");
        bookRecord.setPrice(BigDecimal.ONE);
        bookRecord.setIsbn("123456789");
        bookRecord.setPublisher("Publisher");
        return bookRecord;
    }
}
