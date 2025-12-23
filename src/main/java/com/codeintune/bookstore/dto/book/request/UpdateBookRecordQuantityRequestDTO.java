package com.codeintune.bookstore.dto.book.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateBookRecordQuantityRequestDTO {

    @NotNull(message = "{id.notNull}")
    private Long bookId;
    @NotNull(message = "{quantity.notNull}")
    @Min(value = 1, message = "{quantity.minimum}")
    private Integer quantity;
}
