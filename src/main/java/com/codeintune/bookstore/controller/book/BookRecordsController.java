package com.codeintune.bookstore.controller.book;

import com.codeintune.bookstore.dto.book.response.GetBookRecordsResponseDTO;
import com.codeintune.bookstore.dto.filter.SearchBookRecordsDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
@Tag(
        name = "Book Records",
        description = "Operations related to multiple book records"
)
public interface BookRecordsController {

    @Operation(summary = "Get Books", description = "Obtain paginated list of book records", security = { @SecurityRequirement(name = "bearerAuth") })
    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            name = "Ok",
                            value = "[{" +
                                    "\"bookId\": 1" +
                                    "\"title\":\"Alice In Wonderland\"," +
                                    "\"author\":\"Lewis Carroll\"," +
                                    "\"price\": 10.99," +
                                    "\"isbn\": \"9780062936615\"," +
                                    "\"quantity\": 1," +
                                    "\"publisher\": \"Harper\"" +
                                    " }]"
                    )
            )
    )
    @Parameters({
            @Parameter(name="pageSize", description = "Size of the page returned"),
            @Parameter(name ="pageNumber", description = "Page number returned")
    })
    @GetMapping(
            produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'VIEWER')")
    GetBookRecordsResponseDTO getAllBookRecords(
            Pageable pageRequest
    );

    @Operation(summary = "Get Books Filtered", description = "Obtain paginated list of book records filtered", security = { @SecurityRequirement(name = "bearerAuth") })
    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            name = "Ok",
                            value = "[{" +
                                    "\"id\": 1" +
                                    "\"title\":\"Alice In Wonderland\"," +
                                    "\"author\":\"Lewis Carroll\"," +
                                    "\"price\": 10.99," +
                                    "\"isbn\": \"9780062936615\"," +
                                    "\"quantity\": 1," +
                                    "\"publisher\": \"Harper\"" +
                                    " }]"
                    )
            )
    )
    @Parameters({
            @Parameter(name="pageSize", description = "Size of the page returned"),
            @Parameter(name="pageNumber", description = "Page number returned")
    })
    @PostMapping(
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'VIEWER')")
    GetBookRecordsResponseDTO getBookRecordsByFilter(
            Pageable pageRequest,
            @RequestBody @Valid SearchBookRecordsDTO searchBookRecordsDTO
    );
}
