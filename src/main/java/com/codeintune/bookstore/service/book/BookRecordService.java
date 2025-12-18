package com.codeintune.bookstore.service.book;

import com.codeintune.bookstore.dto.book.request.SaveBookRecordRequestDTO;
import com.codeintune.bookstore.dto.book.request.UpdateBookRecordByIdRequestDTO;
import com.codeintune.bookstore.dto.book.request.UpdateBookRecordQuantityRequestDTO;
import com.codeintune.bookstore.dto.book.response.GetBookRecordByIdResponseDTO;
import com.codeintune.bookstore.dto.book.response.GetBookRecordsResponseDTO;
import com.codeintune.bookstore.dto.book.response.SaveBookRecordResponseDTO;
import com.codeintune.bookstore.dto.book.response.UpdateBookRecordByIdResponseDTO;
import com.codeintune.bookstore.dto.filter.SearchBookRecordsDTO;
import org.springframework.data.domain.PageRequest;

public interface BookRecordService {

    SaveBookRecordResponseDTO saveBookRecord(SaveBookRecordRequestDTO saveBookRecordRequestDTO);
    GetBookRecordByIdResponseDTO getBookRecordById(Long bookId);
    UpdateBookRecordByIdResponseDTO updateBookRecordById(UpdateBookRecordByIdRequestDTO updateBookRecordByIdRequestDTO);
    UpdateBookRecordByIdResponseDTO updateBookRecordQuantity(UpdateBookRecordQuantityRequestDTO updateBookRecordQuantityRequestDTO);
    void removeBookRecordById(Long bookId);
    GetBookRecordsResponseDTO getAllBookRecords(PageRequest pageRequest);
    GetBookRecordsResponseDTO getBookRecordsByFilter(PageRequest pageRequest, SearchBookRecordsDTO searchBookRecordsDTO);
}
