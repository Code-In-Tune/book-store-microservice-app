package com.codeintune.bookstore.controller.book;

import com.codeintune.bookstore.dto.book.response.GetBookRecordsResponseDTO;
import com.codeintune.bookstore.dto.filter.SearchBookRecordsDTO;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

public interface BookRecordsController {

    @GetMapping(
            produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    GetBookRecordsResponseDTO getAllBookRecords(
            Pageable pageRequest
    );

    @PostMapping(
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    GetBookRecordsResponseDTO getBookRecordsByFilter(
            Pageable pageRequest,
            @RequestBody @Valid SearchBookRecordsDTO searchBookRecordsDTO
    );
}
