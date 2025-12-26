package com.codeintune.bookstore.controller.sale;

import com.codeintune.bookstore.dto.sale.request.SaveBookSaleRequestDTO;
import com.codeintune.bookstore.dto.sale.response.SaveBookSaleResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Tag(
        name = "Book Sale",
        description = "Operations related to single book sale record"
)
public interface BookSaleController {

    @Operation(summary = "Save Book Sale", description = "Create new book sale record", security = { @SecurityRequirement(name = "bearerAuth") })
    @ApiResponse(
            responseCode = "201",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            name = "Created",
                            value = "{" +
                                    "\"saleId\": 1" +
                                    "\"bookId\":\"1\"," +
                                    "\"amount\": 10.99," +
                                    "\"quantity\": 1," +
                                    "\"dateSold\": \"26/12/2025\"" +
                                    " }"
                    )
            )
    )
    @PostMapping(
            consumes = MimeTypeUtils.APPLICATION_JSON_VALUE,
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    SaveBookSaleResponseDTO save(@RequestBody @Valid SaveBookSaleRequestDTO saveBookSaleRequestDTO);
}
