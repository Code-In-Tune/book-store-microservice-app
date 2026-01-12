package com.codeintune.bookstore.testutils.asserts;

import com.codeintune.bookstore.dto.book.enums.AvailabilityDTO;
import com.codeintune.bookstore.dto.book.response.GetBookRecordByIdResponseDTO;
import com.codeintune.bookstore.dto.book.response.SaveBookRecordResponseDTO;
import com.codeintune.bookstore.dto.book.response.UpdateBookRecordByIdResponseDTO;

import java.math.BigDecimal;

public final class BookAssertions {

    public static void assertGetBookRecordResponseDTOBase(GetBookRecordByIdResponseDTO getBookRecordByIdResponseDTO) {
        GetBookRecordByIdResponseDTOAssert.assertThat(getBookRecordByIdResponseDTO)
                .hasAuthor("Lewis Carroll")
                .hasTitle("Alice In Wonderland")
                .hasPrice(BigDecimal.ONE)
                .hasQuantity(1)
                .hasAvailabilityDTO(AvailabilityDTO.IN_STOCK)
                .hasBookId(1L)
                .hasISBN("123456789")
                .hasPublisher("Publisher");
    }

    public static void assertSaveBookRecordResponseDTOBase(SaveBookRecordResponseDTO saveBookRecordResponseDTO) {
        SaveBookRecordResponseDTOAssert.assertThat(saveBookRecordResponseDTO)
                .hasAuthor("Lewis Carroll")
                .hasTitle("Alice In Wonderland")
                .hasPrice(BigDecimal.ONE)
                .hasQuantity(1)
                .hasAvailabilityDTO(AvailabilityDTO.IN_STOCK)
                .hasBookId(1L)
                .hasISBN("123456789")
                .hasPublisher("Publisher");
    }

    public static void assertUpdateBookRecordByIdResponseDTOBase(UpdateBookRecordByIdResponseDTO updateBookRecordByIdResponseDTO) {
        UpdateBookRecordByIdResponseDTOAssert.assertThat(updateBookRecordByIdResponseDTO)
                .hasAuthor("Lewis Carroll")
                .hasTitle("Alice Through The Looking Glass")
                .hasPrice(BigDecimal.ONE)
                .hasQuantity(1)
                .hasAvailabilityDTO(AvailabilityDTO.IN_STOCK)
                .hasBookId(1L)
                .hasISBN("123456789")
                .hasPublisher("Publisher");
    }
}
