package com.codeintune.bookstore.testutils.seeder.book.dto;

import com.codeintune.bookstore.dto.book.enums.AvailabilityDTO;
import com.codeintune.bookstore.dto.book.response.SaveBookRecordResponseDTO;

import java.math.BigDecimal;

public class SaveBookRecordResponseDTOTestSeeder {

    public static SaveBookRecordResponseDTO saveBookRecordRequestDTO(){
        SaveBookRecordResponseDTO bookRecord = new SaveBookRecordResponseDTO();
        bookRecord.setQuantity(1);
        bookRecord.setBookId(1L);
        bookRecord.setAvailability(AvailabilityDTO.IN_STOCK);
        bookRecord.setAuthor("Lewis Carroll");
        bookRecord.setTitle("Alice In Wonderland");
        bookRecord.setPrice(BigDecimal.ONE);
        bookRecord.setIsbn("123456789");
        bookRecord.setPublisher("Publisher");
        return bookRecord;
    }
}
