package com.codeintune.bookstore.dto.sale.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class SaveBookSaleResponseDTO {

    private Long saleId;
    private Long bookId;
    private Integer quantity;
    private BigDecimal amount;
    private Instant dateSold;
}
