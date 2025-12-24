package com.codeintune.bookstore.controller.book.impl;

import com.codeintune.bookstore.controller.book.BookRecordController;
import com.codeintune.bookstore.dto.book.request.SaveBookRecordRequestDTO;
import com.codeintune.bookstore.dto.book.request.UpdateBookRecordByIdRequestDTO;
import com.codeintune.bookstore.dto.book.request.UpdateBookRecordQuantityRequestDTO;
import com.codeintune.bookstore.dto.book.response.GetBookRecordByIdResponseDTO;
import com.codeintune.bookstore.dto.book.response.SaveBookRecordResponseDTO;
import com.codeintune.bookstore.dto.book.response.UpdateBookRecordByIdResponseDTO;
import com.codeintune.bookstore.service.book.BookRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookRecordControllerImpl implements BookRecordController {

    private final BookRecordService bookRecordService;

    @Override
    public SaveBookRecordResponseDTO saveBookRecord(SaveBookRecordRequestDTO saveBookRecordRequestDTO) {
        return bookRecordService.saveBookRecord(saveBookRecordRequestDTO);
    }

    @Override
    public GetBookRecordByIdResponseDTO getBookRecordById(Long bookId) {
        return bookRecordService.getBookRecordById(bookId);
    }

    @Override
    public UpdateBookRecordByIdResponseDTO updateBookRecordById(UpdateBookRecordByIdRequestDTO updateBookRecordByIdRequestDTO) {
        return bookRecordService.updateBookRecordById(updateBookRecordByIdRequestDTO);
    }

    @Override
    public UpdateBookRecordByIdResponseDTO updateBookRecordQuantity(UpdateBookRecordQuantityRequestDTO updateBookRecordQuantityRequestDTO) {
        return bookRecordService.updateBookRecordQuantity(updateBookRecordQuantityRequestDTO);
    }

    @Override
    public void deleteBookRecordById(Long bookId) {
        bookRecordService.removeBookRecordById(bookId);
    }
}
