package com.codeintune.bookstore.dto.book.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(name = "GetBookRecordsResponseDTO", description = "Response to obtain a paginated list of book records")
@Data
public class GetBookRecordsResponseDTO {

    private List<GetBookRecordByIdResponseDTO> bookRecords;
    @Schema(example = "1")
    private Integer pageNumber;
    @Schema(example = "10")
    private Integer pageSize;
    @Schema(example = "2")
    private Integer totalPages;
    @Schema(example = "true")
    private Boolean hasNext;
    @Schema(example = "false")
    private Boolean hasPrevious;
}
