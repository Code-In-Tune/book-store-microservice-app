package com.codeintune.bookstore.controller.sale.impl;

import com.codeintune.bookstore.controller.sale.BookSalesController;
import com.codeintune.bookstore.dto.sale.response.GetBookSalesResponseDTO;
import com.codeintune.bookstore.service.sale.BookSaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class BookSalesControllerImpl implements BookSalesController {

    private final BookSaleService bookSaleService;

    @Override
    public GetBookSalesResponseDTO getBookSales(Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());
        return bookSaleService.getSales(pageRequest);
    }
}
