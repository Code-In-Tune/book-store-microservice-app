package com.codeintune.bookstore.dto.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "SearchBookRecordsDTO", description = "Request used to filter book records")
@Data
public class SearchBookRecordsDTO {
    @Schema(name = "title", description = "Title filter field", example = "Alice In Wonderland")
    private String title;
    @Schema(name = "author", description = "Author filter field", example = "Lewis Carroll")
    private String author;
    @Schema(name = "publisher", description = "Publisher filter field", example = "Harper")
    private String publisher;
}
