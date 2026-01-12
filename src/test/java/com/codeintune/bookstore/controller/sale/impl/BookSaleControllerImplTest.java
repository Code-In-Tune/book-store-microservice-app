package com.codeintune.bookstore.controller.sale.impl;

import com.codeintune.bookstore.configuration.message.MessageSourceConfig;
import com.codeintune.bookstore.controller.advice.GlobalExceptionHandler;
import com.codeintune.bookstore.controller.sale.BookSaleController;
import com.codeintune.bookstore.dto.sale.request.SaveBookSaleRequestDTO;
import com.codeintune.bookstore.dto.sale.response.SaveBookSaleResponseDTO;
import com.codeintune.bookstore.exception.BookSaleDomainException;
import com.codeintune.bookstore.service.sale.BookSaleService;
import com.codeintune.bookstore.testutils.seeder.sale.dto.SaveBookSaleRequestDTOTestSeeder;
import com.codeintune.bookstore.testutils.seeder.sale.dto.SaveBookSaleResponseDTOTestSeeder;
import com.codeintune.bookstore.utils.constants.exception.I18NConstants;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.MimeTypeUtils;
import tools.jackson.databind.ObjectMapper;
@WebMvcTest(BookSaleController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import({GlobalExceptionHandler.class, MessageSourceConfig.class})
class BookSaleControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookSaleService bookSaleService;
    private static ObjectMapper objectMapper;

    @BeforeAll
    static void setup(){
        objectMapper = new ObjectMapper();
    }

    @Test
    void save_WhenNoDomainExceptionIsThrown() throws Exception{
        SaveBookSaleRequestDTO request = SaveBookSaleRequestDTOTestSeeder.generateRequest();
        SaveBookSaleResponseDTO response = SaveBookSaleResponseDTOTestSeeder.generateResponse();
        Mockito.when(bookSaleService.save(Mockito.any())).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/sale")
                .content(objectMapper.writeValueAsString(request))
                        .contentType(MimeTypeUtils.APPLICATION_JSON_VALUE)
                        .accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
        )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MimeTypeUtils.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity").value(1));
    }

    @Test
    void save_WhenDomainExceptionIsThrown() throws Exception{
        SaveBookSaleRequestDTO request = SaveBookSaleRequestDTOTestSeeder.generateRequest();
        BookSaleDomainException exception = new BookSaleDomainException(HttpStatus.CONFLICT, "Generic Exception", I18NConstants.CANNOT_SELL_BOOK_RECORD_QUANTITY);
        Mockito.when(bookSaleService.save(Mockito.any())).thenThrow(exception);

        mockMvc.perform(MockMvcRequestBuilders.post("/sale")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MimeTypeUtils.APPLICATION_JSON_VALUE)
                .accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
        )
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.content().contentType(MimeTypeUtils.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(HttpStatus.CONFLICT.value()));
    }
}