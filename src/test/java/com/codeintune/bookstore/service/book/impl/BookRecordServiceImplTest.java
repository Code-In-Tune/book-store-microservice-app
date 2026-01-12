package com.codeintune.bookstore.service.book.impl;

import com.codeintune.bookstore.dto.book.enums.AvailabilityDTO;
import com.codeintune.bookstore.dto.book.request.SaveBookRecordRequestDTO;
import com.codeintune.bookstore.dto.book.request.UpdateBookRecordByIdRequestDTO;
import com.codeintune.bookstore.dto.book.request.UpdateBookRecordQuantityRequestDTO;
import com.codeintune.bookstore.dto.book.response.GetBookRecordByIdResponseDTO;
import com.codeintune.bookstore.dto.book.response.GetBookRecordsResponseDTO;
import com.codeintune.bookstore.dto.book.response.SaveBookRecordResponseDTO;
import com.codeintune.bookstore.dto.book.response.UpdateBookRecordByIdResponseDTO;
import com.codeintune.bookstore.dto.filter.SearchBookRecordsDTO;
import com.codeintune.bookstore.exception.BookRecordDomainException;
import com.codeintune.bookstore.mapper.BookRecordMapper;
import com.codeintune.bookstore.model.book.BookRecord;
import com.codeintune.bookstore.model.book.enums.Availability;
import com.codeintune.bookstore.repository.BookRecordRepository;
import com.codeintune.bookstore.service.book.BookRecordService;
import com.codeintune.bookstore.specification.SpecificationBuilder;
import com.codeintune.bookstore.testutils.seeder.book.data.BookRecordTestSeeder;
import com.codeintune.bookstore.testutils.seeder.book.dto.*;
import lombok.NonNull;
import org.hibernate.jpa.AvailableHints;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {
        BookRecordServiceImpl.class
})
@ExtendWith(SpringExtension.class)
class BookRecordServiceImplTest {

    @Autowired
    private BookRecordService bookRecordService;
    @MockitoBean
    private BookRecordRepository bookRecordRepository;
    @MockitoBean
    private BookRecordMapper bookRecordMapper;
    @MockitoBean
    private SpecificationBuilder<SearchBookRecordsDTO, BookRecord> specificationBuilder;

    @Test
    void saveBookRecord() {
        BookRecord entity = BookRecordTestSeeder.generateEntityMapped();
        SaveBookRecordRequestDTO request = SaveBookRecordRequestDTOTestSeeder.saveBookRecordRequestDTO();
        SaveBookRecordResponseDTO response = SaveBookRecordResponseDTOTestSeeder.saveBookRecordRequestDTO();
        Mockito.when(bookRecordMapper.toEntity(Mockito.any(SaveBookRecordRequestDTO.class)))
                .thenReturn(entity);
        Mockito.doAnswer((invocationOnMock) -> {
            BookRecord entityToBeSaved = invocationOnMock.getArgument(0, BookRecord.class);
            Assertions.assertNull(entityToBeSaved.getBookId());
            entityToBeSaved.setBookId(1L);
            return entityToBeSaved;
        }).when(bookRecordRepository).save(Mockito.any(BookRecord.class));
        Mockito.when(bookRecordMapper.toSaveBookDtoResponse(Mockito.any(BookRecord.class)))
                .thenReturn(response);

        SaveBookRecordResponseDTO output = bookRecordService.saveBookRecord(request);

        Assertions.assertAll(
                () -> Assertions.assertEquals(1L, output.getBookId(), "Book Id do not match"),
                () -> Assertions.assertEquals(1, output.getQuantity(), "Quantity do not match"),
                () -> Assertions.assertEquals("Lewis Carroll", output.getAuthor(), "Author do not match"),
                () -> Assertions.assertEquals("Alice In Wonderland", output.getTitle(), "Title do not match"),
                () -> Assertions.assertEquals(AvailabilityDTO.IN_STOCK, output.getAvailability(), "Availability do not match"),
                () -> Assertions.assertEquals("123456789", output.getIsbn(), "ISBN do not match"),
                () -> Assertions.assertEquals(BigDecimal.ONE, output.getPrice(), "Price do not match"),
                () -> Assertions.assertEquals("Publisher", output.getPublisher(), "Publisher do not match")
        );
    }

    @Test
    void getBookRecordById_WhenNoExceptionThrown() {
        Mockito.when(bookRecordRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(BookRecordTestSeeder.generateEntity()));
        Mockito.when(bookRecordMapper.toGetBookDtoResponse(Mockito.any(BookRecord.class)))
                .thenReturn(GetBookByIdResponseDTOTestSeeder.generateResponse());

        GetBookRecordByIdResponseDTO output = bookRecordService.getBookRecordById(1L);
        Assertions.assertAll(
                () -> Assertions.assertEquals(1L, output.getBookId(), "Book Id do not match"),
                () -> Assertions.assertEquals(1, output.getQuantity(), "Quantity do not match"),
                () -> Assertions.assertEquals("Lewis Carroll", output.getAuthor(), "Author do not match"),
                () -> Assertions.assertEquals("Alice In Wonderland", output.getTitle(), "Title do not match"),
                () -> Assertions.assertEquals(AvailabilityDTO.IN_STOCK, output.getAvailability(), "Availability do not match"),
                () -> Assertions.assertEquals("123456789", output.getIsbn(), "ISBN do not match"),
                () -> Assertions.assertEquals(BigDecimal.ONE, output.getPrice(), "Price do not match"),
                () -> Assertions.assertEquals("Publisher", output.getPublisher(), "Publisher do not match")
        );
    }

    @Test
    void getBookRecordById_WhenExceptionThrown() {
        Mockito.when(bookRecordRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(BookRecordDomainException.class, () -> bookRecordService.getBookRecordById(1L));
    }

    @Test
    void updateBookRecordById_WhenNoExceptionThrown() {
        UpdateBookRecordByIdRequestDTO request = UpdateBookRecordByIDRequestDTOTestSeeder.generateRequest();
        BookRecord entityFound = BookRecordTestSeeder.generateEntity();
        Mockito.when(bookRecordRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(entityFound));

        Mockito.doAnswer((invocationOnMock -> {
            UpdateBookRecordByIdRequestDTO requestFromUpdate =  invocationOnMock.getArgument(0, UpdateBookRecordByIdRequestDTO.class);
            BookRecord recordToUpdate = invocationOnMock.getArgument(1, BookRecord.class);
            Assertions.assertNotEquals(recordToUpdate.getTitle(), requestFromUpdate.getTitle());
            recordToUpdate.setTitle(requestFromUpdate.getTitle()); // Updating the object
            return null;
        })).when(bookRecordMapper).updateEntity(Mockito.any(), Mockito.any());

        Mockito.when(bookRecordRepository.save(Mockito.any(BookRecord.class)))
                .thenReturn(entityFound);
        Mockito.when(bookRecordMapper.toUpdateBookDtoResponse(Mockito.any(BookRecord.class)))
                .thenReturn(UpdateRecordByIdResponseDTOTestSeeder.generateResponse());

        UpdateBookRecordByIdResponseDTO output = bookRecordService.updateBookRecordById(request);


        Assertions.assertAll(
                () -> Assertions.assertEquals(1L, output.getBookId(), "Book Id do not match"),
                () -> Assertions.assertEquals("Lewis Carroll", output.getAuthor(), "Author do not match"),
                () -> Assertions.assertEquals("Alice Through The Looking Glass", output.getTitle(), "Title do not match"),
                () -> Assertions.assertEquals("123456789", output.getIsbn(), "ISBN do not match"),
                () -> Assertions.assertEquals(BigDecimal.ONE, output.getPrice(), "Price do not match"),
                () -> Assertions.assertEquals("Publisher", output.getPublisher(), "Publisher do not match")
        );

    }

    @Test
    public void updateBookRecordById_WhenExceptionThrown() {
        UpdateBookRecordByIdRequestDTO request = UpdateBookRecordByIDRequestDTOTestSeeder.generateRequest();
        Mockito.when(bookRecordRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(BookRecordDomainException.class, () -> bookRecordService.updateBookRecordById(request));
    }

    @Test
    void updateBookRecordQuantity_WhenNoExceptionThrown() {
        UpdateBookRecordQuantityRequestDTO request = UpdateBookRecordQuantityRequestDTOTestSeeder.generateRequest();
        UpdateBookRecordByIdResponseDTO response = UpdateRecordByIdResponseDTOTestSeeder.generateResponse();
        request.setQuantity(1);
        request.setBookId(1L);

        BookRecord entityFound = BookRecordTestSeeder.generateEntity();
        entityFound.setQuantity(0); // OUT OF STOCK
        entityFound.setAvailability(Availability.OUT_OF_STOCK);

        Mockito.when(bookRecordRepository.findById(Mockito.anyLong()))
                        .thenReturn(Optional.of(entityFound));

        Mockito.doAnswer((invocationOnMock -> {
            BookRecord entityToBeSaved = invocationOnMock.getArgument(0, BookRecord.class);
            Assertions.assertEquals(1, entityToBeSaved.getQuantity());
            Assertions.assertEquals(Availability.IN_STOCK, entityToBeSaved.getAvailability());
            return entityToBeSaved;
        })).when(bookRecordRepository).save(Mockito.any(BookRecord.class));


        Mockito.doAnswer((invocationOnMock -> {
            BookRecord entityToBeSaved = invocationOnMock.getArgument(0, BookRecord.class);
            UpdateBookRecordByIdResponseDTO mappedResponse =  UpdateRecordByIdResponseDTOTestSeeder.generateResponse();
            mappedResponse.setQuantity(entityToBeSaved.getQuantity());
            mappedResponse.setAvailability(AvailabilityDTO.IN_STOCK);
            return mappedResponse;
        })).when(bookRecordMapper).toUpdateBookDtoResponse(Mockito.any(BookRecord.class));

        UpdateBookRecordByIdResponseDTO output = bookRecordService.updateBookRecordQuantity(request);
        Assertions.assertEquals(1, output.getQuantity(), "Quantity do not match");
        Assertions.assertEquals(AvailabilityDTO.IN_STOCK, output.getAvailability(), "Availability do not match");
    }

    @Test
    public void updateBookRecordQuantity_WhenExceptionThrown() {
        UpdateBookRecordQuantityRequestDTO request = UpdateBookRecordQuantityRequestDTOTestSeeder.generateRequest();
        Mockito.when(bookRecordRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(BookRecordDomainException.class, () -> bookRecordService.updateBookRecordQuantity(request));
    }

    @Test
    void removeBookRecordById() {
        bookRecordService.removeBookRecordById(1L);
        Mockito.verify(bookRecordRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }

    @Test
    void getAllBookRecords() {
        List<BookRecord> records = new ArrayList<>();
        records.add(BookRecordTestSeeder.generateEntity());
        Page<@NonNull BookRecord> pageResult = new PageImpl<>(records);

        GetBookRecordByIdResponseDTO response = GetBookByIdResponseDTOTestSeeder.generateResponse();

        Mockito.when(bookRecordRepository.findAll(Mockito.any(PageRequest.class)))
                .thenReturn(pageResult);
        Mockito.when(bookRecordMapper.toGetBookDtoResponse(Mockito.any(BookRecord.class)))
                .thenReturn(response);

        GetBookRecordsResponseDTO outputList = bookRecordService.getAllBookRecords(PageRequest.of(0, 10));
        Assertions.assertEquals(1, outputList.getBookRecords().size(), "BookRecords size do not match");
        GetBookRecordByIdResponseDTO output = outputList.getBookRecords().get(0);

        Assertions.assertAll(
                () -> Assertions.assertEquals(1L, output.getBookId(), "Book Id do not match"),
                () -> Assertions.assertEquals("Lewis Carroll", output.getAuthor(), "Author do not match"),
                () -> Assertions.assertEquals("Alice In Wonderland", output.getTitle(), "Title do not match"),
                () -> Assertions.assertEquals("123456789", output.getIsbn(), "ISBN do not match"),
                () -> Assertions.assertEquals(BigDecimal.ONE, output.getPrice(), "Price do not match"),
                () -> Assertions.assertEquals("Publisher", output.getPublisher(), "Publisher do not match")
        );
    }

    @Test
    void getBookRecordsByFilter() {
        SearchBookRecordsDTO searchBookRecordsDTO = new SearchBookRecordsDTO();
        searchBookRecordsDTO.setTitle("Lewis Carroll");
        Mockito.when(specificationBuilder.build(Mockito.any())).thenReturn(Specification.unrestricted());
        List<BookRecord> records = new ArrayList<>();
        records.add(BookRecordTestSeeder.generateEntity());
        Page<@NonNull BookRecord> pageResult = new PageImpl<>(records);

        GetBookRecordByIdResponseDTO response = GetBookByIdResponseDTOTestSeeder.generateResponse();

        Mockito.when(bookRecordRepository.findAll(Mockito.<Specification<@NonNull BookRecord>>any(),Mockito.any(PageRequest.class)))
                .thenReturn(pageResult);
        Mockito.when(bookRecordMapper.toGetBookDtoResponse(Mockito.any(BookRecord.class)))
                .thenReturn(response);

        GetBookRecordsResponseDTO outputList = bookRecordService.getBookRecordsByFilter(PageRequest.of(0, 10), searchBookRecordsDTO);
        Assertions.assertEquals(1, outputList.getBookRecords().size(), "BookRecords size do not match");
        GetBookRecordByIdResponseDTO output = outputList.getBookRecords().get(0);

        Assertions.assertAll(
                () -> Assertions.assertEquals(1L, output.getBookId(), "Book Id do not match"),
                () -> Assertions.assertEquals("Lewis Carroll", output.getAuthor(), "Author do not match"),
                () -> Assertions.assertEquals("Alice In Wonderland", output.getTitle(), "Title do not match"),
                () -> Assertions.assertEquals("123456789", output.getIsbn(), "ISBN do not match"),
                () -> Assertions.assertEquals(BigDecimal.ONE, output.getPrice(), "Price do not match"),
                () -> Assertions.assertEquals("Publisher", output.getPublisher(), "Publisher do not match")
        );
    }
}