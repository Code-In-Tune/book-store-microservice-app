package com.codeintune.bookstore.controller.sale;

import com.codeintune.bookstore.dto.sale.response.GetBookSalesResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(
        name = "Book Sales",
        description = "Operations related to multiple book sale record"
)
public interface BookSalesController {


    @Operation(summary = "Get Book Sales", description = "Get book sale records paginated", security = { @SecurityRequirement(name = "bearerAuth") })
    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = GetBookSalesResponseDTO.class),
                    examples = @ExampleObject(
                            name = "Get Book Sale Records",
                            description = "Returns a list of paginated book sold records",
                            value = """
                                     {
                                     "bookSales":
                                     [{
                                     "saleId": 1,
                                     "bookId": 1,
                                     "amount": 10.99,
                                     "quantity": 1,
                                     "dateSold": "2025-12-27T12:56:39.000691700Z"
                                     }],
                                     "pageSize": 1,
                                     "pageNumber": 1,
                                     "totalPages": 1,
                                     "hasNext": true,
                                     "hasPrevious": false
                                     }
                                     """
                    )
            )
    )
    @GetMapping(
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    GetBookSalesResponseDTO getBookSales(Pageable pageable);
}
