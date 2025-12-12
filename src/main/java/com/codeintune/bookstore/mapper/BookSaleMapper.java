package com.codeintune.bookstore.mapper;

import com.codeintune.bookstore.dto.sale.request.SaveBookSaleRequestDTO;
import com.codeintune.bookstore.dto.sale.response.GetBookSaleByIdResponseDTO;
import com.codeintune.bookstore.dto.sale.response.SaveBookSaleResponseDTO;
import com.codeintune.bookstore.model.sale.BookSale;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookSaleMapper {

    BookSale toEntity(SaveBookSaleRequestDTO dto);
    SaveBookSaleResponseDTO toSaveBookSaleDtoResponse(BookSale entity);

    GetBookSaleByIdResponseDTO toGetBookSaleByIdDtoResponse(BookSale entity);
}
