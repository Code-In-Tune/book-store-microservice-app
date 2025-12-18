package com.codeintune.bookstore.dto.book.request;

import lombok.Data;

@Data
public class UpdateBookRecordQuantityRequestDTO {

    private Long bookId;
    private Integer quantity;
}
