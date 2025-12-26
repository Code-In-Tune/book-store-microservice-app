package com.codeintune.bookstore.controller.book;

import com.codeintune.bookstore.dto.book.request.SaveBookRecordRequestDTO;
import com.codeintune.bookstore.dto.book.request.UpdateBookRecordByIdRequestDTO;
import com.codeintune.bookstore.dto.book.request.UpdateBookRecordQuantityRequestDTO;
import com.codeintune.bookstore.dto.book.response.GetBookRecordByIdResponseDTO;
import com.codeintune.bookstore.dto.book.response.SaveBookRecordResponseDTO;
import com.codeintune.bookstore.dto.book.response.UpdateBookRecordByIdResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Tag(
        name = "Book Record",
        description = "Operations related to single book record"
)
public interface BookRecordController {

    String ID_PATH = "/{bookId}";

    @Operation(summary = "Save Book", description = "Create new book record", security = { @SecurityRequirement(name = "bearerAuth") })
    @ApiResponse(
            responseCode = "201",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            name = "Created",
                            value = "{" +
                                    "\"id\": 1" +
                                    "\"title\":\"Alice In Wonderland\"," +
                                    "\"author\":\"Lewis Carroll\"," +
                                    "\"price\": 10.99," +
                                    "\"isbn\": \"9780062936615\"," +
                                    "\"quantity\": 1," +
                                    "\"publisher\": \"Harper\"" +
                                    " }"
                    )
            )
    )
    @PostMapping(
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    SaveBookRecordResponseDTO saveBookRecord(
            @RequestBody @Valid SaveBookRecordRequestDTO saveBookRecordRequestDTO
    );

    @Operation(summary = "Get Book", description = "Obtain an existing book record", security = { @SecurityRequirement(name = "bearerAuth") })
    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            name = "Ok",
                            value = "{" +
                                    "\"id\": 1" +
                                    "\"title\":\"Alice In Wonderland\"," +
                                    "\"author\":\"Lewis Carroll\"," +
                                    "\"price\": 10.99," +
                                    "\"isbn\": \"9780062936615\"," +
                                    "\"quantity\": 1," +
                                    "\"publisher\": \"Harper\"" +
                                    " }"
                    )
            )
    )
    @Parameter(name = "bookId", description = "The book id of the record", required = true, example = "1")
    @GetMapping(
            value = ID_PATH,
            produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'VIEWER')")
    GetBookRecordByIdResponseDTO getBookRecordById(
            @PathVariable(name = "bookId") Long bookId
    );

    @Operation(summary = "Update Book", description = "Update an existing book record", security = { @SecurityRequirement(name = "bearerAuth") })
    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            name = "Ok",
                            value = "{" +
                                    "\"id\": 1" +
                                    "\"title\":\"Alice In Wonderland\"," +
                                    "\"author\":\"Lewis Carroll\"," +
                                    "\"price\": 10.99," +
                                    "\"isbn\": \"9780062936615\"," +
                                    "\"quantity\": 1," +
                                    "\"publisher\": \"Harper\"" +
                                    " }"
                    )
            )
    )
    @PutMapping(
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    UpdateBookRecordByIdResponseDTO updateBookRecordById(
            @RequestBody @Valid UpdateBookRecordByIdRequestDTO updateBookRecordByIdRequestDTO
    );

    @Operation(summary = "Update Book By Quantity", description = "Increment book quantity on existing book record", security = { @SecurityRequirement(name = "bearerAuth") })
    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            name = "Ok",
                            value = "{" +
                                    "\"id\": 1" +
                                    "\"title\":\"Alice In Wonderland\"," +
                                    "\"author\":\"Lewis Carroll\"," +
                                    "\"price\": 10.99," +
                                    "\"isbn\": \"9780062936615\"," +
                                    "\"quantity\": 1," +
                                    "\"publisher\": \"Harper\"" +
                                    " }"
                    )
            )
    )
    @PatchMapping(
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    UpdateBookRecordByIdResponseDTO updateBookRecordQuantity(
            @RequestBody @Valid UpdateBookRecordQuantityRequestDTO updateBookRecordQuantityRequestDTO
    );


    @Operation(summary = "Delete Book", description = "Remove an existing book record", security = { @SecurityRequirement(name = "bearerAuth") })
    @ApiResponse(
            responseCode = "204",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            name = "No Content",
                            value = "{" +
                                    " }"
                    )
            )
    )
    @Parameter(name = "bookId", description = "The book id of the record", required = true, example = "1")
    @DeleteMapping(
            value = ID_PATH
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    void deleteBookRecordById(@PathVariable(name = "bookId") Long bookId);

}
