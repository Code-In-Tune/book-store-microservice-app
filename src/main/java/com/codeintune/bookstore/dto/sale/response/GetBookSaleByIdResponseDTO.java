package com.codeintune.bookstore.dto.sale.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(name = "GetBookSaleByIdResponseDTO", description = "Response to obtain a single book sale record")
@Data
public class GetBookSaleByIdResponseDTO {
    @Schema(example = "1")
    private Long saleId;
    @Schema(example = "1")
    private Long bookId;
    @Schema(example = "1")
    private Integer quantity;
    @Schema(example = "10.99")
    private BigDecimal amount;
}
