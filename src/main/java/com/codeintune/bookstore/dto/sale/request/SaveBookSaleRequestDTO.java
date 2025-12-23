package com.codeintune.bookstore.dto.sale.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SaveBookSaleRequestDTO {

    @NotNull(message = "{id.notNull}")
    private Long bookId;
    @NotNull(message = "{quantity.notNull}")
    @Min(value = 1, message = "{quantity.minimum}")
    private Integer quantity;
    @NotNull(message = "{payment.amount.notNull}")
    @Digits(integer = 10, fraction = 2, message = "{payment.amount.digits}")
    private BigDecimal amount;
}
