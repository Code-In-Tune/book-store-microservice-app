package com.codeintune.bookstore.dto.sale.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GetBookSaleByIdResponseDTO {

    private Long saleId;
    private Long bookId;
    private Integer quantity;
    private BigDecimal amount;
}
