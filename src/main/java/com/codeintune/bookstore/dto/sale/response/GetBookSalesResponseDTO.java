package com.codeintune.bookstore.dto.sale.response;

import lombok.Data;

import java.util.List;

@Data
public class GetBookSalesResponseDTO {

    private List<GetBookSaleByIdResponseDTO> bookSales;
}
