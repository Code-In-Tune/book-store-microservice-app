package com.codeintune.bookstore.dto.sale.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SaveBookSaleRequestDTO {

    private Long bookId;
    private Integer quantity;
    private BigDecimal amount;
}
