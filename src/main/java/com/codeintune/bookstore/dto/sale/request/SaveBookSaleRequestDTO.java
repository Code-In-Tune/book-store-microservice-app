package com.codeintune.bookstore.dto.sale.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Schema(
        name = "SaveBookSaleRequestDTO",
        description = "Represents a request to create new book sale record"
)
@Data
public class SaveBookSaleRequestDTO {

    @Schema(
            name = "bookId",
            description = "Id of the book record sold",
            example = "1",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "{id.notNull}")
    private Long bookId;
    @Schema(
            name = "quantity",
            description = "Quantity of the book record sold",
            example = "1",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "{quantity.notNull}")
    @Min(value = 1, message = "{quantity.minimum}")
    private Integer quantity;
}
