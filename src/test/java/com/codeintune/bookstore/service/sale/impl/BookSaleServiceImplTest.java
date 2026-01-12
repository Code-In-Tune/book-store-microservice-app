package com.codeintune.bookstore.service.sale.impl;

import com.codeintune.bookstore.dto.sale.request.SaveBookSaleRequestDTO;
import com.codeintune.bookstore.dto.sale.response.GetBookSaleByIdResponseDTO;
import com.codeintune.bookstore.dto.sale.response.GetBookSalesResponseDTO;
import com.codeintune.bookstore.dto.sale.response.SaveBookSaleResponseDTO;
import com.codeintune.bookstore.exception.BookRecordDomainException;
import com.codeintune.bookstore.exception.BookSaleDomainException;
import com.codeintune.bookstore.mapper.BookSaleMapper;
import com.codeintune.bookstore.model.book.BookRecord;
import com.codeintune.bookstore.model.sale.BookSale;
import com.codeintune.bookstore.repository.BookRecordRepository;
import com.codeintune.bookstore.repository.BookSaleRepository;
import com.codeintune.bookstore.service.sale.BookSaleService;
import com.codeintune.bookstore.testutils.asserts.BookSaleAssertions;
import com.codeintune.bookstore.testutils.asserts.SaveBookSaleResponseDTOAssert;
import com.codeintune.bookstore.testutils.seeder.book.data.BookRecordTestSeeder;
import com.codeintune.bookstore.testutils.seeder.sale.data.BookSaleTestSeeder;
import com.codeintune.bookstore.testutils.seeder.sale.dto.GetBookSaleByIdResponseDTOTestSeeder;
import com.codeintune.bookstore.testutils.seeder.sale.dto.SaveBookSaleRequestDTOTestSeeder;
import com.codeintune.bookstore.testutils.seeder.sale.dto.SaveBookSaleResponseDTOTestSeeder;
import lombok.NonNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ContextConfiguration(classes = {
        BookSaleServiceImpl.class,
})
@ExtendWith(SpringExtension.class)
class BookSaleServiceImplTest {

    @Autowired
    private BookSaleService bookSaleService;
    @MockitoBean
    private BookSaleRepository bookSaleRepository;
    @MockitoBean
    private BookRecordRepository bookRecordRepository;
    @MockitoBean
    private BookSaleMapper bookSaleMapper;


    @Test
    void save_WhenNoExceptionIsThrown() {
        SaveBookSaleRequestDTO request = SaveBookSaleRequestDTOTestSeeder.generateRequest();
        SaveBookSaleResponseDTO response = SaveBookSaleResponseDTOTestSeeder.generateResponse();
        BookRecord recordFound = BookRecordTestSeeder.generateEntity();
        Mockito.when(bookRecordRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(recordFound));
        Mockito.doAnswer((invocationOnMock -> {
            BookSale bookSale = invocationOnMock.getArgument(0, BookSale.class);
            bookSale.setSaleId(1L);
            return bookSale;
        })).when(bookSaleRepository).save(Mockito.any(BookSale.class));

        Mockito.when(bookSaleMapper.toSaveBookSaleDtoResponse(Mockito.any(BookSale.class)))
                .thenReturn(response);

        SaveBookSaleResponseDTO output = bookSaleService.save(request);

        BookSaleAssertions.assertSaveBookSaleResponseDTO(output);
    }

    @Test
    void save_WhenExceptionNotFoundIsThrown() {
        SaveBookSaleRequestDTO request = SaveBookSaleRequestDTOTestSeeder.generateRequest();
        Mockito.when(bookRecordRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(BookRecordDomainException.class, () ->bookSaleService.save(request));
    }

    @Test
    void save_WhenExceptionImpossibleToSellIsThrown(){
        SaveBookSaleRequestDTO request = SaveBookSaleRequestDTOTestSeeder.generateRequest();
        BookRecord recordFound = BookRecordTestSeeder.generateEntity();
        recordFound.setQuantity(0); // Set the unavailability
        Mockito.when(bookRecordRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(recordFound));

        Assertions.assertThrows(BookSaleDomainException.class, () ->bookSaleService.save(request));
    }

    @Test
    void getSales_WhenNoExceptionIsThrown() {
        List<BookSale> records = new ArrayList<>();
        records.add(BookSaleTestSeeder.generateEntity());
        GetBookSaleByIdResponseDTO response = GetBookSaleByIdResponseDTOTestSeeder.generateResponse();
        Page<@NonNull BookSale> pageResult = new PageImpl<>(records);
        PageRequest pageRequest = PageRequest.of(0, 10);

        Mockito.when(bookSaleRepository.findAll(Mockito.any(PageRequest.class)))
                .thenReturn(pageResult);
        Mockito.when(bookSaleMapper.toGetBookSaleByIdDtoResponse(Mockito.any(BookSale.class)))
                .thenReturn(response);

        GetBookSalesResponseDTO outputList = bookSaleService.getSales(pageRequest);
        Assertions.assertEquals(1, outputList.getBookSales().size());
        GetBookSaleByIdResponseDTO output = outputList.getBookSales().get(0);

        BookSaleAssertions.assertGetBookSaleByIdResponseDTO(output);
    }
}