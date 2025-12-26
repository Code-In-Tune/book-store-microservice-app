package com.codeintune.bookstore.dto.book.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(
        name = "UpdateBookRecordQuantityRequestDTO",
        description = "Represents a request to update an existing book record quantity"
)
@Data
public class UpdateBookRecordQuantityRequestDTO {

    @Schema(
            name = "bookId",
            description = "Id of the book record",
            example = "1",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "{id.notNull}")
    private Long bookId;
    @Schema(
            name = "quantity",
            description = "Quantity of the book record",
            example = "2",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "{quantity.notNull}")
    @Min(value = 1, message = "{quantity.minimum}")
    private Integer quantity;
}
