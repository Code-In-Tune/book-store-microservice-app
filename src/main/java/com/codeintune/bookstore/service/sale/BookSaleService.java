package com.codeintune.bookstore.service.sale;

import com.codeintune.bookstore.dto.sale.request.SaveBookSaleRequestDTO;
import com.codeintune.bookstore.dto.sale.response.GetBookSalesResponseDTO;
import com.codeintune.bookstore.dto.sale.response.SaveBookSaleResponseDTO;
import org.springframework.data.domain.PageRequest;

public interface BookSaleService {

    SaveBookSaleResponseDTO save(SaveBookSaleRequestDTO saveBookSaleRequestDTO);
    GetBookSalesResponseDTO getSales(PageRequest pageRequest);
}
