package com.codeintune.bookstore.controller.sale.impl;

import com.codeintune.bookstore.configuration.message.MessageSourceConfig;
import com.codeintune.bookstore.controller.advice.GlobalExceptionHandler;
import com.codeintune.bookstore.controller.sale.BookSalesController;
import com.codeintune.bookstore.dto.sale.response.GetBookSaleByIdResponseDTO;
import com.codeintune.bookstore.dto.sale.response.GetBookSalesResponseDTO;
import com.codeintune.bookstore.service.sale.BookSaleService;
import com.codeintune.bookstore.testutils.seeder.sale.dto.GetBookSaleByIdResponseDTOTestSeeder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.MimeTypeUtils;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(BookSalesController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import({GlobalExceptionHandler.class, MessageSourceConfig.class})
class BookSalesControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookSaleService bookSaleService;


    @Test
    void getBookSales_WhenNoExceptionIsBeingThrown() throws Exception {
        List<GetBookSaleByIdResponseDTO> responses = new ArrayList<>();
        responses.add(GetBookSaleByIdResponseDTOTestSeeder.generateResponse());
        GetBookSalesResponseDTO response = new GetBookSalesResponseDTO();
        response.setBookSales(responses);

        Mockito.when(bookSaleService.getSales(Mockito.any(PageRequest.class)))
                        .thenReturn(response);


        mockMvc.perform(MockMvcRequestBuilders.get("/sales")
                .queryParam("page","0")
                .queryParam("size","10")
                .accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
                .contentType(MimeTypeUtils.APPLICATION_JSON_VALUE)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MimeTypeUtils.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookSales[0].quantity").value(1));
    }
}