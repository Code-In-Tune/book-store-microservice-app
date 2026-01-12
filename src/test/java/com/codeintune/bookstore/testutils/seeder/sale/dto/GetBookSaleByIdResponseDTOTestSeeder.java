package com.codeintune.bookstore.testutils.seeder.sale.dto;

import com.codeintune.bookstore.dto.sale.response.GetBookSaleByIdResponseDTO;

import java.math.BigDecimal;

public class GetBookSaleByIdResponseDTOTestSeeder {

    public static GetBookSaleByIdResponseDTO generateResponse(){
        GetBookSaleByIdResponseDTO responseDTO = new GetBookSaleByIdResponseDTO();
        responseDTO.setBookId(1L);
        responseDTO.setQuantity(1);
        responseDTO.setSaleId(1L);
        responseDTO.setAmount(BigDecimal.ONE);
        return responseDTO;
    }
}
