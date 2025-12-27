package com.codeintune.bookstore.service.sale.impl;

import com.codeintune.bookstore.dto.sale.request.SaveBookSaleRequestDTO;
import com.codeintune.bookstore.dto.sale.response.GetBookSaleByIdResponseDTO;
import com.codeintune.bookstore.dto.sale.response.GetBookSalesResponseDTO;
import com.codeintune.bookstore.dto.sale.response.SaveBookSaleResponseDTO;
import com.codeintune.bookstore.exception.BookRecordDomainException;
import com.codeintune.bookstore.exception.BookSaleDomainException;
import com.codeintune.bookstore.mapper.BookSaleMapper;
import com.codeintune.bookstore.model.book.BookRecord;
import com.codeintune.bookstore.model.book.enums.Availability;
import com.codeintune.bookstore.model.sale.BookSale;
import com.codeintune.bookstore.repository.BookRecordRepository;
import com.codeintune.bookstore.repository.BookSaleRepository;
import com.codeintune.bookstore.service.sale.BookSaleService;
import com.codeintune.bookstore.utils.constants.exception.BookRecordDomainExceptionConstants;
import com.codeintune.bookstore.utils.constants.exception.BookSaleDomainExceptionConstants;
import com.codeintune.bookstore.utils.constants.exception.I18NConstants;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookSaleServiceImpl implements BookSaleService {

    private final BookSaleRepository bookSaleRepository;
    private final BookRecordRepository bookRecordRepository;
    private final BookSaleMapper bookSaleMapper;


    @Override
    @Transactional
    public SaveBookSaleResponseDTO save(SaveBookSaleRequestDTO saveBookSaleRequestDTO) {
        Integer quantitySold =  saveBookSaleRequestDTO.getQuantity();
        Long bookId = saveBookSaleRequestDTO.getBookId();

        BookRecord record = bookRecordRepository.findById(bookId).orElseThrow(
                () -> new BookRecordDomainException(HttpStatus.NOT_FOUND,
                        BookRecordDomainExceptionConstants.BOOK_NOT_FOUND_MESSAGE.formatted(bookId),
                        I18NConstants.BOOK_RECORD_NOT_FOUND_KEY,
                        bookId)
        );

        if(record.getQuantity() < quantitySold) {
            throw new BookSaleDomainException(HttpStatus.CONFLICT,
                    BookSaleDomainExceptionConstants.CANNOT_SELL_GIVEN_QUANTITY
                            .formatted(quantitySold, record.getQuantity()),
                    I18NConstants.CANNOT_SELL_BOOK_RECORD_QUANTITY);
        }
        record.setQuantity(record.getQuantity() - quantitySold);
        if(record.getQuantity() == 0){
            record.setAvailability(Availability.OUT_OF_STOCK);
        }
        BookSale entityToSave = new BookSale();
        entityToSave.setBookId(bookId);
        entityToSave.setQuantity(quantitySold);
        entityToSave.setAmount(record.getPrice().multiply(BigDecimal.valueOf(quantitySold)));
        entityToSave.setDateSold(Instant.now());
        bookSaleRepository.save(entityToSave);
        return bookSaleMapper.toSaveBookSaleDtoResponse(entityToSave);
    }

    @Override
    public GetBookSalesResponseDTO getSales(PageRequest pageRequest) {
        Page<@NonNull BookSale> page = bookSaleRepository.findAll(pageRequest);
        List<GetBookSaleByIdResponseDTO> sales = page
                .getContent().stream().map(bookSaleMapper::toGetBookSaleByIdDtoResponse).toList();
        GetBookSalesResponseDTO response = new GetBookSalesResponseDTO();
        response.setBookSales(sales);
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setHasNext(page.hasNext());
        response.setHasPrevious(page.hasPrevious());
        return response;
    }
}
