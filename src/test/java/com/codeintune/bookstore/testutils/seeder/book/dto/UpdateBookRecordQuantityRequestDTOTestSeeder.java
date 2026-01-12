package com.codeintune.bookstore.testutils.seeder.book.dto;

import com.codeintune.bookstore.dto.book.request.UpdateBookRecordQuantityRequestDTO;

public class UpdateBookRecordQuantityRequestDTOTestSeeder {

    public static UpdateBookRecordQuantityRequestDTO generateRequest(){
        UpdateBookRecordQuantityRequestDTO bookRecord = new UpdateBookRecordQuantityRequestDTO();
        bookRecord.setBookId(1L);
        bookRecord.setQuantity(1);
        return bookRecord;
    }
}
