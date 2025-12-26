package com.codeintune.bookstore.controller.book;

import com.codeintune.bookstore.dto.book.request.SaveBookRecordRequestDTO;
import com.codeintune.bookstore.dto.book.request.UpdateBookRecordByIdRequestDTO;
import com.codeintune.bookstore.dto.book.request.UpdateBookRecordQuantityRequestDTO;
import com.codeintune.bookstore.dto.book.response.GetBookRecordByIdResponseDTO;
import com.codeintune.bookstore.dto.book.response.SaveBookRecordResponseDTO;
import com.codeintune.bookstore.dto.book.response.UpdateBookRecordByIdResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

public interface BookRecordController {

    String ID_PATH = "/{bookId}";

    @PostMapping(
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    SaveBookRecordResponseDTO saveBookRecord(
            @RequestBody @Valid SaveBookRecordRequestDTO saveBookRecordRequestDTO
    );

    @GetMapping(
            value = ID_PATH,
            produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'VIEWER')")
    GetBookRecordByIdResponseDTO getBookRecordById(
            @PathVariable(name = "bookId") Long bookId
    );

    @PutMapping(
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    UpdateBookRecordByIdResponseDTO updateBookRecordById(
            @RequestBody @Valid UpdateBookRecordByIdRequestDTO updateBookRecordByIdRequestDTO
    );

    @PatchMapping(
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    UpdateBookRecordByIdResponseDTO updateBookRecordQuantity(
            @RequestBody @Valid UpdateBookRecordQuantityRequestDTO updateBookRecordQuantityRequestDTO
    );
    @DeleteMapping(
            value = ID_PATH
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    void deleteBookRecordById(@PathVariable(name = "bookId") Long bookId);

}
