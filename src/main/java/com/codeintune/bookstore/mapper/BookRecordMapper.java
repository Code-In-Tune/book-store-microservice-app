package com.codeintune.bookstore.mapper;

import com.codeintune.bookstore.dto.book.request.SaveBookRecordRequestDTO;
import com.codeintune.bookstore.dto.book.request.UpdateBookRecordByIdRequestDTO;
import com.codeintune.bookstore.dto.book.response.GetBookRecordByIdResponseDTO;
import com.codeintune.bookstore.dto.book.response.SaveBookRecordResponseDTO;
import com.codeintune.bookstore.dto.book.response.UpdateBookRecordByIdResponseDTO;
import com.codeintune.bookstore.model.book.BookRecord;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface BookRecordMapper {


    BookRecord toEntity(SaveBookRecordRequestDTO dto);

    SaveBookRecordResponseDTO toSaveBookDtoResponse(BookRecord bookRecord);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(UpdateBookRecordByIdRequestDTO dto, @MappingTarget BookRecord bookRecord);

    UpdateBookRecordByIdResponseDTO toUpdateBookDtoResponse(BookRecord bookRecord);

    GetBookRecordByIdResponseDTO toGetBookDtoResponse(BookRecord bookRecord);
}
