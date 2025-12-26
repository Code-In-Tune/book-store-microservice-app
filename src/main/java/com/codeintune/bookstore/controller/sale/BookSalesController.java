package com.codeintune.bookstore.controller.sale;

import com.codeintune.bookstore.dto.sale.response.GetBookSalesResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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
            responseCode = "201",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            name = "Created",
                            value = "[{" +
                                    "\"saleId\": 1" +
                                    "\"bookId\":\"1\"," +
                                    "\"amount\": 10.99," +
                                    "\"quantity\": 1," +
                                    "\"dateSold\": \"26/12/2025\"" +
                                    " }]"
                    )
            )
    )
    @Parameters({
            @Parameter(name="pageSize", description = "Size of the page returned"),
            @Parameter(name="pageNumber", description = "Page number returned")
    })
    @GetMapping(
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    GetBookSalesResponseDTO getBookSales(Pageable pageable);
}
