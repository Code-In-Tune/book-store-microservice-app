package com.codeintune.bookstore.controller.book.impl;

import com.codeintune.bookstore.configuration.message.MessageSourceConfig;
import com.codeintune.bookstore.controller.advice.GlobalExceptionHandler;
import com.codeintune.bookstore.dto.book.request.SaveBookRecordRequestDTO;
import com.codeintune.bookstore.dto.book.request.UpdateBookRecordByIdRequestDTO;
import com.codeintune.bookstore.dto.book.request.UpdateBookRecordQuantityRequestDTO;
import com.codeintune.bookstore.dto.book.response.GetBookRecordByIdResponseDTO;
import com.codeintune.bookstore.dto.book.response.SaveBookRecordResponseDTO;
import com.codeintune.bookstore.dto.book.response.UpdateBookRecordByIdResponseDTO;
import com.codeintune.bookstore.exception.BookRecordDomainException;
import com.codeintune.bookstore.service.book.BookRecordService;
import com.codeintune.bookstore.testutils.seeder.book.dto.*;
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

@WebMvcTest(BookRecordControllerImpl.class)
@AutoConfigureMockMvc(addFilters = false)
@Import({GlobalExceptionHandler.class, MessageSourceConfig.class})
class BookRecordControllerImplTest {

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
    void saveBookRecord_WhenNoExceptionIsBeingThrown() throws Exception {
        SaveBookRecordRequestDTO request = SaveBookRecordRequestDTOTestSeeder.saveBookRecordRequestDTO();
        SaveBookRecordResponseDTO response = SaveBookRecordResponseDTOTestSeeder.saveBookRecordRequestDTO();

        request.setIsbn("9780062936615"); // Valid ISBN, non mock ISBN

        Mockito.when(bookRecordService.saveBookRecord(Mockito.any(SaveBookRecordRequestDTO.class)))
                .thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/book")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MimeTypeUtils.APPLICATION_JSON_VALUE)
                        .accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MimeTypeUtils.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("Lewis Carroll"));
    }

    @Test
    void getBookRecordById_WhenNoExceptionIsBeingThrown() throws Exception {
        GetBookRecordByIdResponseDTO response = GetBookByIdResponseDTOTestSeeder.generateResponse();
        Mockito.when(bookRecordService.getBookRecordById(Mockito.anyLong()))
                .thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/book/{id}", 1L)
                        .contentType(MimeTypeUtils.APPLICATION_JSON_VALUE)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MimeTypeUtils.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("Lewis Carroll"));
    }

    @Test
    void getBookRecordById_WhenExceptionIsBeingThrown() throws Exception {
        BookRecordDomainException exception = new BookRecordDomainException(HttpStatus.NOT_FOUND, "Mock Message", I18NConstants.BOOK_RECORD_NOT_FOUND_KEY, 1L);
        Mockito.when(bookRecordService.getBookRecordById(Mockito.anyLong()))
                .thenThrow(exception);

        mockMvc.perform(MockMvcRequestBuilders.get("/book/{id}", 1L)
                        .contentType(MimeTypeUtils.APPLICATION_JSON_VALUE)
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MimeTypeUtils.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    void updateBookRecordById_WhenNoExceptionIsBeingThrown() throws Exception {
        UpdateBookRecordByIdRequestDTO request = UpdateBookRecordByIDRequestDTOTestSeeder.generateRequest();
        UpdateBookRecordByIdResponseDTO response = UpdateRecordByIdResponseDTOTestSeeder.generateResponse();

        request.setIsbn("9780062936615"); // Valid ISBN, non mock ISBN

        Mockito.when(bookRecordService.updateBookRecordById(Mockito.any(UpdateBookRecordByIdRequestDTO.class)))
                .thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.put("/book")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MimeTypeUtils.APPLICATION_JSON_VALUE)
                        .accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MimeTypeUtils.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("Lewis Carroll"));
    }

    @Test
    void updateBookRecordQuantity_WhenNoExceptionIsBeingThrown() throws Exception {
        UpdateBookRecordQuantityRequestDTO request = UpdateBookRecordQuantityRequestDTOTestSeeder.generateRequest();
        UpdateBookRecordByIdResponseDTO response = UpdateRecordByIdResponseDTOTestSeeder.generateResponse();

        Mockito.when(bookRecordService.updateBookRecordQuantity(Mockito.any(UpdateBookRecordQuantityRequestDTO.class)))
                .thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.patch("/book")
                        .content(objectMapper.writeValueAsString(request))
                        .accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
                        .contentType(MimeTypeUtils.APPLICATION_JSON_VALUE)
                        .accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MimeTypeUtils.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity").value(1));
    }

    @Test
    void deleteBookRecordById_WhenNoExceptionIsBeingThrown() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/book/{id}", 1L)
                )
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}