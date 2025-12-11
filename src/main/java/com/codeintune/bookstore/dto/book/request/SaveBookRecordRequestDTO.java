package com.codeintune.bookstore.dto.book.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SaveBookRecordRequestDTO {

    private String title;
    private String author;
    private String publisher;
    private BigDecimal price;
    private Integer quantity;
}
