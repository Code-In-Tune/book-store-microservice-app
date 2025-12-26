package com.codeintune.bookstore.controller.sale;

import com.codeintune.bookstore.dto.sale.request.SaveBookSaleRequestDTO;
import com.codeintune.bookstore.dto.sale.response.SaveBookSaleResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface BookSaleController {

    @PostMapping(
            consumes = MimeTypeUtils.APPLICATION_JSON_VALUE,
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    SaveBookSaleResponseDTO save(@RequestBody @Valid SaveBookSaleRequestDTO saveBookSaleRequestDTO);
}
