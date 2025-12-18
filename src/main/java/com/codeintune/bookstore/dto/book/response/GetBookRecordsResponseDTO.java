package com.codeintune.bookstore.dto.book.response;

import lombok.Data;

import java.util.List;

@Data
public class GetBookRecordsResponseDTO {

    private List<GetBookRecordByIdResponseDTO> bookRecords;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalPages;
    private Boolean hasNext;
    private Boolean hasPrevious;
}
