package com.codeintune.bookstore.dto.sale.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(name = "GetBookSalesResponseDTO", description = "Response to obtain a paginated list of book sale records")
@Data
public class GetBookSalesResponseDTO {

    private List<GetBookSaleByIdResponseDTO> bookSales;
}
