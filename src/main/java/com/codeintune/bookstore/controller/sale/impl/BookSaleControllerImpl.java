package com.codeintune.bookstore.controller.sale.impl;

import com.codeintune.bookstore.controller.sale.BookSaleController;
import com.codeintune.bookstore.dto.sale.request.SaveBookSaleRequestDTO;
import com.codeintune.bookstore.dto.sale.response.SaveBookSaleResponseDTO;
import com.codeintune.bookstore.service.sale.BookSaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sale")
@RequiredArgsConstructor
public class BookSaleControllerImpl implements BookSaleController {

    private final BookSaleService bookSaleService;

    @Override
    public SaveBookSaleResponseDTO save(SaveBookSaleRequestDTO saveBookSaleRequestDTO) {
        return bookSaleService.save(saveBookSaleRequestDTO);
    }
}
