package com.codeintune.bookstore.controller.book.impl;

import com.codeintune.bookstore.controller.book.BookRecordsController;
import com.codeintune.bookstore.dto.book.response.GetBookRecordsResponseDTO;
import com.codeintune.bookstore.dto.filter.SearchBookRecordsDTO;
import com.codeintune.bookstore.service.book.BookRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookRecordsControllerImpl implements BookRecordsController {

    private final BookRecordService bookRecordService;

    @Override
    public GetBookRecordsResponseDTO getAllBookRecords(Pageable pageRequest) {
        PageRequest request = PageRequest.of(
                pageRequest.getPageNumber(),
                pageRequest.getPageSize()
        );
        return bookRecordService.getAllBookRecords(request);
    }

    @Override
    public GetBookRecordsResponseDTO getBookRecordsByFilter(Pageable pageRequest, SearchBookRecordsDTO searchBookRecordsDTO) {
        PageRequest request = PageRequest.of(
                pageRequest.getPageNumber(),
                pageRequest.getPageSize()
        );
        return bookRecordService.getBookRecordsByFilter(request, searchBookRecordsDTO);
    }
}
