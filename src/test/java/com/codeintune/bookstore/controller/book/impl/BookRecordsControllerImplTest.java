package com.codeintune.bookstore.controller.book.impl;

import com.codeintune.bookstore.configuration.message.MessageSourceConfig;
import com.codeintune.bookstore.controller.advice.GlobalExceptionHandler;
import com.codeintune.bookstore.dto.book.response.GetBookRecordByIdResponseDTO;
import com.codeintune.bookstore.dto.book.response.GetBookRecordsResponseDTO;
import com.codeintune.bookstore.dto.filter.SearchBookRecordsDTO;
import com.codeintune.bookstore.service.book.BookRecordService;
import com.codeintune.bookstore.testutils.seeder.book.dto.GetBookByIdResponseDTOTestSeeder;
import org.junit.jupiter.api.BeforeAll;
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
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(BookRecordsControllerImpl.class)
@AutoConfigureMockMvc(addFilters = false)
@Import({GlobalExceptionHandler.class, MessageSourceConfig.class})
class BookRecordsControllerImplTest {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private BookRecordService bookRecordService;

    private static ObjectMapper objectMapper;

    @BeforeAll
    static void setup() {
        objectMapper = new ObjectMapper();
    }


    @Test
    void getAllBookRecords_WhenNoExceptionIsBeingThrown() throws Exception {
        List<GetBookRecordByIdResponseDTO> bookRecords = new ArrayList<>();
        bookRecords.add(GetBookByIdResponseDTOTestSeeder.generateResponse());
        GetBookRecordsResponseDTO response = new GetBookRecordsResponseDTO();
        response.setBookRecords(bookRecords);

        Mockito.when(bookRecordService.getAllBookRecords(Mockito.any(PageRequest.class)))
                .thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/books")
                        .queryParam("page", "0")
                        .queryParam("size", "10")
                        .accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
                        .contentType(MimeTypeUtils.APPLICATION_JSON_VALUE)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MimeTypeUtils.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookRecords[0].author").value("Lewis Carroll"));

    }

    @Test
    void getBookRecordsByFilter_WhenNoExceptionIsBeingThrown() throws Exception {
        SearchBookRecordsDTO searchBookRecordsDTO = new SearchBookRecordsDTO();
        searchBookRecordsDTO.setTitle("Lewis Carroll");
        List<GetBookRecordByIdResponseDTO> bookRecords = new ArrayList<>();
        bookRecords.add(GetBookByIdResponseDTOTestSeeder.generateResponse());
        GetBookRecordsResponseDTO response = new GetBookRecordsResponseDTO();
        response.setBookRecords(bookRecords);

        Mockito.when(bookRecordService.getBookRecordsByFilter(Mockito.any(PageRequest.class), Mockito.any(SearchBookRecordsDTO.class)))
                .thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                        .content(objectMapper.writeValueAsString(searchBookRecordsDTO))
                        .queryParam("page", "0")
                        .queryParam("size", "10")
                        .accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
                        .contentType(MimeTypeUtils.APPLICATION_JSON_VALUE)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MimeTypeUtils.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookRecords[0].author").value("Lewis Carroll"));

    }
}