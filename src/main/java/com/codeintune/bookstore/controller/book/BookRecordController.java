package com.codeintune.bookstore.controller.book;

import com.codeintune.bookstore.dto.book.request.SaveBookRecordRequestDTO;
import com.codeintune.bookstore.dto.book.request.UpdateBookRecordByIdRequestDTO;
import com.codeintune.bookstore.dto.book.request.UpdateBookRecordQuantityRequestDTO;
import com.codeintune.bookstore.dto.book.response.GetBookRecordByIdResponseDTO;
import com.codeintune.bookstore.dto.book.response.SaveBookRecordResponseDTO;
import com.codeintune.bookstore.dto.book.response.UpdateBookRecordByIdResponseDTO;
import com.codeintune.bookstore.dto.error.ApiError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Save Book", description = "Create new book record", security = {@SecurityRequirement(name = "bearerAuth")})
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SaveBookRecordResponseDTO.class),
                            examples = @ExampleObject(
                                    name = "Created",
                                    description = "Returns data relative to book record created",
                                    value = """
                                            {
                                            "bookId": 1,
                                            "title": "Alice In Wonderland",
                                            "author": "Lewis Carroll",
                                            "price": 10.99,
                                            "isbn": "9780062936615",
                                            "quantity": 1,
                                            "publisher": "Harper"
                                            }
                                            """

                            )
                    )
            )
    })
    @PostMapping(
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    SaveBookRecordResponseDTO saveBookRecord(
            @RequestBody @Valid SaveBookRecordRequestDTO saveBookRecordRequestDTO
    );

    @Operation(summary = "Get Book", description = "Obtain an existing book record", security = {@SecurityRequirement(name = "bearerAuth")})
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GetBookRecordByIdResponseDTO.class),
                            examples = @ExampleObject(
                                    name = "Ok",
                                    description = "Returns given book record data",
                                    value = """
                                            {
                                            "bookId": 1,
                                            "title": "Alice In Wonderland",
                                            "author": "Lewis Carroll",
                                            "price": 10.99,
                                            "isbn": "9780062936615",
                                            "quantity": 1,
                                            "publisher": "Harper"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class),
                            examples = @ExampleObject(
                                    name = "NotFound",
                                    description = "Returns an error message with given id not found",
                                    value = """
                                            {
                                            "message": "Book record with id 1 not found",
                                            "status": 404,
                                            "timestamp": "2025-12-27T12:56:39.000691700Z"
                                            }
                                            """
                            )
                    )
            ),
    })
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

    @Operation(summary = "Update Book", description = "Update an existing book record", security = {@SecurityRequirement(name = "bearerAuth")})
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GetBookRecordByIdResponseDTO.class),
                            examples = @ExampleObject(
                                    name = "Ok",
                                    description = "Returns data relative to book record updated",
                                    value = """
                                            {
                                            "bookId": 1,
                                            "title": "Alice In Wonderland",
                                            "author": "Lewis Carroll",
                                            "price": 10.99,
                                            "isbn": "9780062936615",
                                            "quantity": 1,
                                            "publisher": "Harper"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class),
                            examples = @ExampleObject(
                                    name = "NotFound",
                                    description = "Returns an error message with given id not found",
                                    value = """
                                            {
                                            "message": "Book record with id 1 not found",
                                            "status": 404,
                                            "timestamp": "2025-12-27T12:56:39.000691700Z"
                                            }
                                            """
                            )
                    )
            ),
    })
    @PutMapping(
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    UpdateBookRecordByIdResponseDTO updateBookRecordById(
            @RequestBody @Valid UpdateBookRecordByIdRequestDTO updateBookRecordByIdRequestDTO
    );

    @Operation(summary = "Update Book By Quantity", description = "Increment book quantity on existing book record", security = {@SecurityRequirement(name = "bearerAuth")})
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GetBookRecordByIdResponseDTO.class),
                            examples = @ExampleObject(
                                    name = "Ok",
                                    description = "Returns data relative to book record updated",
                                    value = """
                                            {
                                            "bookId": 1,
                                            "title": "Alice In Wonderland",
                                            "author": "Lewis Carroll",
                                            "price": 10.99,
                                            "isbn": "9780062936615",
                                            "quantity": 1,
                                            "publisher": "Harper"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class),
                            examples = @ExampleObject(
                                    name = "NotFound",
                                    description = "Returns an error message with given id not found",
                                    value = """
                                            {
                                            "message": "Book record with id 1 not found",
                                            "status": 404,
                                            "timestamp": "2025-12-27T12:56:39.000691700Z"
                                            }
                                            """
                            )
                    )
            ),
    })
    @PatchMapping(
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    UpdateBookRecordByIdResponseDTO updateBookRecordQuantity(
            @RequestBody @Valid UpdateBookRecordQuantityRequestDTO updateBookRecordQuantityRequestDTO
    );


    @Operation(summary = "Delete Book", description = "Remove an existing book record", security = {@SecurityRequirement(name = "bearerAuth")})
    @ApiResponse(
            responseCode = "204",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            name = "No Content",
                            value = """
                                    {}
                                    """
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
