package com.codeintune.bookstore.testutils.seeder.book.dto;

import com.codeintune.bookstore.dto.book.enums.AvailabilityDTO;
import com.codeintune.bookstore.dto.book.response.UpdateBookRecordByIdResponseDTO;

import java.math.BigDecimal;

public class UpdateRecordByIdResponseDTOTestSeeder {

    public static UpdateBookRecordByIdResponseDTO generateResponse(){
        UpdateBookRecordByIdResponseDTO bookRecord = new UpdateBookRecordByIdResponseDTO();
        bookRecord.setBookId(1L);
        bookRecord.setAuthor("Lewis Carroll");
        bookRecord.setTitle("Alice Through The Looking Glass");
        bookRecord.setPrice(BigDecimal.ONE);
        bookRecord.setAvailability(AvailabilityDTO.IN_STOCK);
        bookRecord.setIsbn("123456789");
        bookRecord.setQuantity(1);
        bookRecord.setPublisher("Publisher");
        return bookRecord;
    }
}
