package com.codeintune.bookstore.dto.book.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateBookRecordByIdRequestDTO {

    @NotNull(message = "{id.notNull}")
    private Long bookId;
    @NotBlank(message = "{title.notBlank}")
    private String title;
    @NotBlank(message = "{author.notBlank}")
    private String author;
    @NotBlank(message = "{publisher.notBlank}")
    private String publisher;
    @NotBlank(message = "{isbn.notBlank}")
    @Pattern(regexp = "^(?:[0-9]{9}[0-9X]|97[89][0-9]{10})$", message = "{isbn.pattern}")
    private String isbn;
    @NotNull(message = "{payment.amount.notNull}")
    @Digits(integer= 10, fraction = 2, message = "{payment.amount.digits}")
    private BigDecimal price;
    @NotNull(message = "{quantity.notNull}")
    @Min(value = 1, message = "{quantity.minimum}")
    private Integer quantity;
}
