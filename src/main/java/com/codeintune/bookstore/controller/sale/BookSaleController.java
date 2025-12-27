package com.codeintune.bookstore.controller.sale;

import com.codeintune.bookstore.dto.error.ApiError;
import com.codeintune.bookstore.dto.sale.request.SaveBookSaleRequestDTO;
import com.codeintune.bookstore.dto.sale.response.SaveBookSaleResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
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
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(
        name = "Book Sale",
        description = "Operations related to single book sale record"
)
public interface BookSaleController {

    @Operation(summary = "Save Book Sale", description = "Create new book sale record", security = {@SecurityRequirement(name = "bearerAuth")})
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SaveBookSaleResponseDTO.class),
                            examples = @ExampleObject(
                                    description = "Returns data relative to book sale created",
                                    name = "Created",
                                    value = """
                                            {
                                            "saleId": 1,
                                            "bookId": 1,
                                            "amount": 10.99,
                                            "quantity": 1,
                                            "dateSold": "2025-12-27T12:56:39.000691700Z"
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
                                    description = "Returns an error message with given id not found",
                                    name = "NotFound",
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
            @ApiResponse(
                    responseCode = "409",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class),
                            examples = @ExampleObject(
                                    description = "Returns an error message with given id not found",
                                    name = "NotFound",
                                    value = """
                                            {
                                            "message": "Book record cannot be sold, too few items",
                                            "status": 409,
                                            "timestamp": "2025-12-27T12:56:39.000691700Z"
                                            }
                                            """
                            )
                    )
            )})
    @PostMapping(
            consumes = MimeTypeUtils.APPLICATION_JSON_VALUE,
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    SaveBookSaleResponseDTO save(@RequestBody @Valid SaveBookSaleRequestDTO saveBookSaleRequestDTO);
}
