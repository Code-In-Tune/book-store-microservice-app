package com.codeintune.bookstore.dto.book.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
@Schema(
        name = "UpdateBookRecordByIdRequestDTO",
        description = "Represents a request to update an existing book record"
)
@Data
public class UpdateBookRecordByIdRequestDTO {

    @Schema(
            name = "bookId",
            description = "Id of the book record",
            example = "1",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "{id.notNull}")
    private Long bookId;
    @Schema(
            name = "title",
            description = "Title of the book record",
            example = "Alice in Wonderland",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "{title.notBlank}")
    private String title;
    @Schema(
            name = "author",
            description = "Author of the book record",
            example = "Lewis Carroll",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "{author.notBlank}")
    private String author;
    @Schema(
            name = "publisher",
            description = "Publisher house of the book record",
            example = "Harper",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "{publisher.notBlank}")
    private String publisher;
    @Schema(
            name = "isbn",
            description = "ISBN of the book record",
            example = "9780062936615",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "{isbn.notBlank}")
    @Pattern(regexp = "^(?:[0-9]{9}[0-9X]|97[89][0-9]{10})$", message = "{isbn.pattern}")
    private String isbn;
    @Schema(
            name = "price",
            description = "Price of the book record",
            example = "10.2",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "{payment.amount.notNull}")
    @Positive(message = "{payment.amount.positive}")
    @Digits(integer= 10, fraction = 2, message = "{payment.amount.digits}")
    private BigDecimal price;
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
