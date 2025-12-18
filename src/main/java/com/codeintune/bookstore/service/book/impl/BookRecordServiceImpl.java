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
import com.codeintune.bookstore.repository.BookRecordRepository;
import com.codeintune.bookstore.service.book.BookRecordService;
import com.codeintune.bookstore.specification.SpecificationBuilder;
import com.codeintune.bookstore.utils.constants.exception.BookRecordDomainExceptionConstants;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookRecordServiceImpl implements BookRecordService {

    private final BookRecordRepository bookRecordRepository;
    private final BookRecordMapper bookRecordMapper;
    private final List<SpecificationBuilder<SearchBookRecordsDTO, BookRecord>> specificationBuilders;

    @Override
    public SaveBookRecordResponseDTO saveBookRecord(SaveBookRecordRequestDTO saveBookRecordRequestDTO) {
        BookRecord entityToBeSaved = bookRecordMapper.toEntity(saveBookRecordRequestDTO);
        entityToBeSaved = bookRecordRepository.save(entityToBeSaved);
        return bookRecordMapper.toSaveBookDtoResponse(entityToBeSaved);
    }

    @Override
    public GetBookRecordByIdResponseDTO getBookRecordById(Long bookId) {
        BookRecord entityFound = bookRecordRepository.findById(bookId).orElseThrow(
                () -> new BookRecordDomainException(HttpStatus.NOT_FOUND,
                        BookRecordDomainExceptionConstants.BOOK_NOT_FOUND_MESSAGE.formatted(bookId))
        );
        return bookRecordMapper.toGetBookDtoResponse(entityFound);
    }

    @Override
    public UpdateBookRecordByIdResponseDTO updateBookRecordById(UpdateBookRecordByIdRequestDTO updateBookRecordByIdRequestDTO) {
        BookRecord entityFound = bookRecordRepository.findById(updateBookRecordByIdRequestDTO.getBookId()).orElseThrow(
                () -> new BookRecordDomainException(HttpStatus.NOT_FOUND,
                        BookRecordDomainExceptionConstants.BOOK_NOT_FOUND_MESSAGE.formatted(updateBookRecordByIdRequestDTO.getBookId()))
        );

        bookRecordMapper.updateEntity(updateBookRecordByIdRequestDTO, entityFound);
        bookRecordRepository.save(entityFound);
        return bookRecordMapper.toUpdateBookDtoResponse(entityFound);
    }

    @Override
    public UpdateBookRecordByIdResponseDTO updateBookRecordQuantity(UpdateBookRecordQuantityRequestDTO updateBookRecordQuantityRequestDTO) {
        BookRecord entityFound = bookRecordRepository.findById(updateBookRecordQuantityRequestDTO.getBookId()).orElseThrow(
                () -> new BookRecordDomainException(HttpStatus.NOT_FOUND,
                        BookRecordDomainExceptionConstants.BOOK_NOT_FOUND_MESSAGE.formatted(updateBookRecordQuantityRequestDTO.getBookId()))
        );
        entityFound.setQuantity(entityFound.getQuantity() + updateBookRecordQuantityRequestDTO.getQuantity());
        bookRecordRepository.save(entityFound);
        return bookRecordMapper.toUpdateBookDtoResponse(entityFound);
    }

    @Override
    public void removeBookRecordById(Long bookId) {
        bookRecordRepository.deleteById(bookId);
    }

    @Override
    public GetBookRecordsResponseDTO getAllBookRecords(PageRequest pageRequest) {
        List<GetBookRecordByIdResponseDTO> books = bookRecordRepository.findAll(pageRequest).map(bookRecordMapper::toGetBookDtoResponse).toList();
        GetBookRecordsResponseDTO getBookRecordsResponseDTO = new GetBookRecordsResponseDTO();
        getBookRecordsResponseDTO.setBookRecords(books);
        return getBookRecordsResponseDTO;
    }

    @Override
    public GetBookRecordsResponseDTO getBookRecordsByFilter(PageRequest pageRequest, SearchBookRecordsDTO searchBookRecordsDTO) {
        Specification<@NonNull BookRecord> specification = specificationBuilders.stream().map(sb -> sb.build(searchBookRecordsDTO))
                .reduce(Specification::and).orElse(Specification.unrestricted());
        List<GetBookRecordByIdResponseDTO> books = bookRecordRepository.findAll(specification,pageRequest).map(bookRecordMapper::toGetBookDtoResponse).toList();
        GetBookRecordsResponseDTO getBookRecordsResponseDTO = new GetBookRecordsResponseDTO();
        getBookRecordsResponseDTO.setBookRecords(books);
        return getBookRecordsResponseDTO;
    }
}
