package com.codeintune.bookstore.dto.book.response;

import com.codeintune.bookstore.dto.book.enums.AvailabilityDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(name = "UpdateBookRecordByIdResponseDTO", description = "Response returned after an update operation")
@Data
public class UpdateBookRecordByIdResponseDTO {

    @Schema(example = "1")
    private Long bookId;
    @Schema(example = "Alice In Wonderland")
    private String title;
    @Schema(example = "Lewis Carroll")
    private String author;
    @Schema(example = "Harper")
    private String publisher;
    @Schema(example = "9780062936615")
    private String isbn;
    @Schema(example = "10.99")
    private BigDecimal price;
    @Schema(example = "2")
    private Integer quantity;
    @Schema(example = "IN_STOCK")
    private AvailabilityDTO availability;
}
