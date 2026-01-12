package com.codeintune.bookstore.testutils.seeder.sale.dto;

import com.codeintune.bookstore.dto.sale.request.SaveBookSaleRequestDTO;

public class SaveBookSaleRequestDTOTestSeeder {

    public static SaveBookSaleRequestDTO generateRequest(){
        SaveBookSaleRequestDTO request = new SaveBookSaleRequestDTO();
        request.setBookId(1L);
        request.setQuantity(1);
        return request;
    }
}
