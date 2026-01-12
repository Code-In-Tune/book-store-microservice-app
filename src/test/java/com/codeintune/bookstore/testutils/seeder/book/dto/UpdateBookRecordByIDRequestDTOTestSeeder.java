package com.codeintune.bookstore.testutils.seeder.book.dto;

import com.codeintune.bookstore.dto.book.request.UpdateBookRecordByIdRequestDTO;

import java.math.BigDecimal;

public class UpdateBookRecordByIDRequestDTOTestSeeder {


    public static UpdateBookRecordByIdRequestDTO generateRequest(){
        UpdateBookRecordByIdRequestDTO bookRecord = new UpdateBookRecordByIdRequestDTO();
        bookRecord.setBookId(1L);
        bookRecord.setAuthor("Lewis Carroll");
        bookRecord.setTitle("Alice Through The Looking Glass");
        bookRecord.setPrice(BigDecimal.ONE);
        bookRecord.setIsbn("123456789");
        bookRecord.setPublisher("Publisher");
        return bookRecord;
    }
}
