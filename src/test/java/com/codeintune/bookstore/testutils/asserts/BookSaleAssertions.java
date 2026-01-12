package com.codeintune.bookstore.testutils.asserts;

import com.codeintune.bookstore.dto.sale.response.GetBookSaleByIdResponseDTO;
import com.codeintune.bookstore.dto.sale.response.SaveBookSaleResponseDTO;

import java.math.BigDecimal;

public class BookSaleAssertions {

    public static void assertSaveBookSaleResponseDTO(SaveBookSaleResponseDTO saveBookSaleResponseDTO) {
        SaveBookSaleResponseDTOAssert.assertThat(saveBookSaleResponseDTO)
                .hasBookId(1L)
                .hasSaleId(1L)
                .hasAmount(BigDecimal.ONE)
                .hasQuantity(1);
    }

    public static void assertGetBookSaleByIdResponseDTO(GetBookSaleByIdResponseDTO responseDTO) {
        GetBookSaleByIdResponseDTOAssert.assertThat(responseDTO)
                .hasBookId(1L)
                .hasSaleId(1L)
                .hasAmount(BigDecimal.ONE)
                .hasQuantity(1);
    }
}
