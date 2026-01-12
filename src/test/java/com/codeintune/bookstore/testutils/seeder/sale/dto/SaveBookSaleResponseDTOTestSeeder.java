package com.codeintune.bookstore.testutils.seeder.sale.dto;

import com.codeintune.bookstore.dto.sale.response.SaveBookSaleResponseDTO;

import java.math.BigDecimal;
import java.time.Instant;

public class SaveBookSaleResponseDTOTestSeeder {

    public static SaveBookSaleResponseDTO generateResponse(){
        SaveBookSaleResponseDTO responseDTO = new SaveBookSaleResponseDTO();
        responseDTO.setBookId(1L);
        responseDTO.setQuantity(1);
        responseDTO.setSaleId(1L);
        responseDTO.setAmount(BigDecimal.ONE);
        responseDTO.setDateSold(Instant.ofEpochMilli(0));
        return responseDTO;
    }
}
