package com.codeintune.bookstore.service.book.impl;

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
import com.codeintune.bookstore.utils.constants.exception.BookRecordDomainExceptionConstants;
import com.codeintune.bookstore.utils.constants.exception.I18NConstants;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookRecordServiceImpl implements BookRecordService {

    private final BookRecordRepository bookRecordRepository;
    private final BookRecordMapper bookRecordMapper;
    private final List<SpecificationBuilder<SearchBookRecordsDTO, BookRecord>> specificationBuilders;

    @Override
    @Transactional
    public SaveBookRecordResponseDTO saveBookRecord(SaveBookRecordRequestDTO saveBookRecordRequestDTO) {
        BookRecord entityToBeSaved = bookRecordMapper.toEntity(saveBookRecordRequestDTO);
        entityToBeSaved.setAvailability(Availability.IN_STOCK);
        entityToBeSaved = bookRecordRepository.save(entityToBeSaved);
        log.info("Book saved successfully, bookId={}", entityToBeSaved.getBookId());
        return bookRecordMapper.toSaveBookDtoResponse(entityToBeSaved);
    }

    @Override
    public GetBookRecordByIdResponseDTO getBookRecordById(Long bookId) {
        BookRecord entityFound = findByIdOrThrowException(bookId);
        log.info("Book retrieved successfully, bookId={}", bookId);
        return bookRecordMapper.toGetBookDtoResponse(entityFound);
    }

    @Override
    @Transactional
    public UpdateBookRecordByIdResponseDTO updateBookRecordById(UpdateBookRecordByIdRequestDTO updateBookRecordByIdRequestDTO) {
        BookRecord entityFound = findByIdOrThrowException(updateBookRecordByIdRequestDTO.getBookId());

        bookRecordMapper.updateEntity(updateBookRecordByIdRequestDTO, entityFound);
        bookRecordRepository.save(entityFound);
        log.info("Book updated successfully, bookId={}", entityFound.getBookId());
        return bookRecordMapper.toUpdateBookDtoResponse(entityFound);
    }

    @Override
    @Transactional
    public UpdateBookRecordByIdResponseDTO updateBookRecordQuantity(UpdateBookRecordQuantityRequestDTO updateBookRecordQuantityRequestDTO) {
        BookRecord entityFound = findByIdOrThrowException(updateBookRecordQuantityRequestDTO.getBookId());
        entityFound.setQuantity(entityFound.getQuantity() + updateBookRecordQuantityRequestDTO.getQuantity());
        bookRecordRepository.save(entityFound);
        log.info("Book quantity updated successfully, bookId={}, quantity={}", entityFound.getBookId(), entityFound.getQuantity());
        return bookRecordMapper.toUpdateBookDtoResponse(entityFound);
    }

    @Override
    @Transactional
    public void removeBookRecordById(Long bookId) {
        bookRecordRepository.deleteById(bookId);
    }

    @Override
    public GetBookRecordsResponseDTO getAllBookRecords(PageRequest pageRequest) {
        Page<@NonNull BookRecord> page = bookRecordRepository.findAll(pageRequest);
        List<GetBookRecordByIdResponseDTO> books = page.map(bookRecordMapper::toGetBookDtoResponse).toList();
        GetBookRecordsResponseDTO getBookRecordsResponseDTO = new GetBookRecordsResponseDTO();
        getBookRecordsResponseDTO.setBookRecords(books);
        setPageFields(page, getBookRecordsResponseDTO);
        log.info("Books retrieved successfully, numItems={}, numPages={}", page.getSize(), page.getTotalPages());
        return getBookRecordsResponseDTO;
    }

    @Override
    public GetBookRecordsResponseDTO getBookRecordsByFilter(PageRequest pageRequest, SearchBookRecordsDTO searchBookRecordsDTO) {
        Specification<@NonNull BookRecord> specification = specificationBuilders.stream().map(sb -> sb.build(searchBookRecordsDTO))
                .reduce(Specification::and).orElse(Specification.unrestricted());
        Page<@NonNull BookRecord> page = bookRecordRepository.findAll(specification, pageRequest);
        List<GetBookRecordByIdResponseDTO> books = page.map(bookRecordMapper::toGetBookDtoResponse).toList();
        GetBookRecordsResponseDTO getBookRecordsResponseDTO = new GetBookRecordsResponseDTO();
        getBookRecordsResponseDTO.setBookRecords(books);
        setPageFields(page, getBookRecordsResponseDTO);
        log.info("Books retrieved successfully, numItems={}, numPages={}", page.getSize(), page.getTotalPages());
        return getBookRecordsResponseDTO;
    }

    private void setPageFields(Page<@NonNull BookRecord> page, GetBookRecordsResponseDTO response){
        response.setTotalPages(page.getTotalPages());
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setHasNext(page.hasNext());
        response.setHasPrevious(page.hasPrevious());
    }

    private BookRecord findByIdOrThrowException(Long bookId) {
        return bookRecordRepository.findById(bookId).orElseThrow(
                () -> new BookRecordDomainException(HttpStatus.NOT_FOUND,
                        BookRecordDomainExceptionConstants.BOOK_NOT_FOUND_MESSAGE.formatted(bookId),
                        I18NConstants.BOOK_RECORD_NOT_FOUND_KEY,
                        bookId)
        );
    }
}
