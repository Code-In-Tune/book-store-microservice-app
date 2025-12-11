package com.codeintune.bookstore.dto.book.response;

import com.codeintune.bookstore.dto.book.enums.AvailabilityDTO;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class GetBookRecordByIdResponseDTO {

    private Long bookId;
    private String title;
    private String author;
    private String publisher;
    private BigDecimal price;
    private Integer quantity;
    private AvailabilityDTO availability;
}
