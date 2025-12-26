package com.codeintune.bookstore.dto.sale.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
@Schema(name = "SaveBookSaleResponseDTO", description = "Response to obtain a saved book sale")

@Data
public class SaveBookSaleResponseDTO {
    @Schema(example = "1")
    private Long saleId;
    @Schema(example = "1")
    private Long bookId;
    @Schema(example = "1")
    private Integer quantity;
    @Schema(example = "10.99")
    private BigDecimal amount;
    @Schema(example = "26/12/2025")
    private Instant dateSold;
}
