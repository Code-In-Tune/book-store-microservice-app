package com.codeintune.bookstore.testutils.asserts;

import com.codeintune.bookstore.dto.book.enums.AvailabilityDTO;
import com.codeintune.bookstore.dto.book.response.UpdateBookRecordByIdResponseDTO;
import org.assertj.core.api.AbstractAssert;

import java.math.BigDecimal;

public class UpdateBookRecordByIdResponseDTOAssert extends AbstractAssert<UpdateBookRecordByIdResponseDTOAssert, UpdateBookRecordByIdResponseDTO> {


    protected UpdateBookRecordByIdResponseDTOAssert(UpdateBookRecordByIdResponseDTO updateBookRecordByIdResponseDTO, Class<?> selfType) {
        super(updateBookRecordByIdResponseDTO, selfType);
    }

    public static UpdateBookRecordByIdResponseDTOAssert assertThat(UpdateBookRecordByIdResponseDTO bookRecord) {
        return new UpdateBookRecordByIdResponseDTOAssert(bookRecord, UpdateBookRecordByIdResponseDTOAssert.class);
    }

    public UpdateBookRecordByIdResponseDTOAssert hasTitle(String title){
        isNotNull();
        if(!actual.getTitle().equals(title)) {
            failWithMessage("Expected title to be <%s> but was <%s>", title, actual.getTitle());
        }
        return this;
    }

    public UpdateBookRecordByIdResponseDTOAssert hasAuthor(String author){
        isNotNull();
        if(!actual.getAuthor().equals(author)) {
            failWithMessage("Expected author to be <%s> but was <%s>", author, actual.getAuthor());
        }
        return this;
    }

    public UpdateBookRecordByIdResponseDTOAssert hasISBN(String isbn){
        isNotNull();
        if(!actual.getIsbn().equals(isbn)) {
            failWithMessage("Expected ISBN to be <%s> but was <%s>", isbn, actual.getIsbn());
        }
        return this;
    }

    public UpdateBookRecordByIdResponseDTOAssert hasQuantity(int quantity){
        isNotNull();
        if(!actual.getQuantity().equals(quantity)) {
            failWithMessage("Expected quantity to be <%s> but was <%s>", quantity, actual.getQuantity());
        }
        return this;
    }

    public UpdateBookRecordByIdResponseDTOAssert hasPrice(BigDecimal price){
        isNotNull();
        if(!actual.getPrice().equals(price)) {
            failWithMessage("Expected price to be <%s> but was <%s>", price, actual.getPrice());
        }
        return this;
    }

    public UpdateBookRecordByIdResponseDTOAssert hasAvailabilityDTO(AvailabilityDTO availabilityDTO){
        isNotNull();
        if(!actual.getAvailability().equals(availabilityDTO)) {
            failWithMessage("Expected availability to be <%s> but was <%s>", availabilityDTO, actual.getAvailability());
        }
        return this;
    }

    public UpdateBookRecordByIdResponseDTOAssert hasBookId(Long bookId){
        isNotNull();
        if(!actual.getBookId().equals(bookId)) {
            failWithMessage("Expected book id to be <%s> but was <%s>", bookId, actual.getBookId());
        }
        return this;
    }

    public UpdateBookRecordByIdResponseDTOAssert hasPublisher(String publisher){
        isNotNull();
        if(!actual.getPublisher().equals(publisher)) {
            failWithMessage("Expected book publisher to be <%s> but was <%s>", publisher, actual.getPublisher());
        }
        return this;
    }
}
