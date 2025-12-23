package com.codeintune.bookstore.dto.book.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SaveBookRecordRequestDTO {

    @NotBlank(message = "{title.notBlank}")
    private String title;
    @NotBlank(message = "{author.notBlank}")
    private String author;
    @NotBlank(message = "{publisher.notBlank}")
    private String publisher;
    @NotNull(message = "{payment.amount.notNull}")
    @Digits(integer = 10, fraction = 2, message = "{payment.amount.digits}")
    private BigDecimal price;
    @NotNull(message = "{quantity.notNull}")
    @Min(value = 1, message = "{quantity.minimum}")
    private Integer quantity;
}
