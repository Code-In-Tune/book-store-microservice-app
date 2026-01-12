package com.codeintune.bookstore.testutils.seeder.book.dto;

import com.codeintune.bookstore.dto.book.enums.AvailabilityDTO;
import com.codeintune.bookstore.dto.book.response.GetBookRecordByIdResponseDTO;

import java.math.BigDecimal;

public class GetBookByIdResponseDTOTestSeeder {

    public static GetBookRecordByIdResponseDTO generateResponse(){
        GetBookRecordByIdResponseDTO bookRecord = new GetBookRecordByIdResponseDTO();
        bookRecord.setBookId(1L);
        bookRecord.setQuantity(1);
        bookRecord.setAvailability(AvailabilityDTO.IN_STOCK);
        bookRecord.setAuthor("Lewis Carroll");
        bookRecord.setTitle("Alice In Wonderland");
        bookRecord.setPrice(BigDecimal.ONE);
        bookRecord.setIsbn("123456789");
        bookRecord.setPublisher("Publisher");
        return bookRecord;
    }
}
