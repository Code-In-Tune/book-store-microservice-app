package com.codeintune.bookstore.testutils.asserts;

import com.codeintune.bookstore.dto.book.enums.AvailabilityDTO;
import com.codeintune.bookstore.dto.book.response.SaveBookRecordResponseDTO;
import org.assertj.core.api.AbstractAssert;

import java.math.BigDecimal;

public class SaveBookRecordResponseDTOAssert extends AbstractAssert<SaveBookRecordResponseDTOAssert, SaveBookRecordResponseDTO> {

    protected SaveBookRecordResponseDTOAssert(SaveBookRecordResponseDTO saveBookRecordResponseDTO, Class<?> selfType) {
        super(saveBookRecordResponseDTO, selfType);
    }

    public static SaveBookRecordResponseDTOAssert assertThat(SaveBookRecordResponseDTO bookRecord) {
        return new SaveBookRecordResponseDTOAssert(bookRecord, SaveBookRecordResponseDTOAssert.class);
    }

    public SaveBookRecordResponseDTOAssert hasTitle(String title){
        isNotNull();
        if(!actual.getTitle().equals(title)) {
            failWithMessage("Expected title to be <%s> but was <%s>", title, actual.getTitle());
        }
        return this;
    }

    public SaveBookRecordResponseDTOAssert hasAuthor(String author){
        isNotNull();
        if(!actual.getAuthor().equals(author)) {
            failWithMessage("Expected author to be <%s> but was <%s>", author, actual.getAuthor());
        }
        return this;
    }

    public SaveBookRecordResponseDTOAssert hasISBN(String isbn){
        isNotNull();
        if(!actual.getIsbn().equals(isbn)) {
            failWithMessage("Expected ISBN to be <%s> but was <%s>", isbn, actual.getIsbn());
        }
        return this;
    }

    public SaveBookRecordResponseDTOAssert hasQuantity(int quantity){
        isNotNull();
        if(!actual.getQuantity().equals(quantity)) {
            failWithMessage("Expected quantity to be <%s> but was <%s>", quantity, actual.getQuantity());
        }
        return this;
    }

    public SaveBookRecordResponseDTOAssert hasPrice(BigDecimal price){
        isNotNull();
        if(!actual.getPrice().equals(price)) {
            failWithMessage("Expected price to be <%s> but was <%s>", price, actual.getPrice());
        }
        return this;
    }

    public SaveBookRecordResponseDTOAssert hasAvailabilityDTO(AvailabilityDTO availabilityDTO){
        isNotNull();
        if(!actual.getAvailability().equals(availabilityDTO)) {
            failWithMessage("Expected availability to be <%s> but was <%s>", availabilityDTO, actual.getAvailability());
        }
        return this;
    }

    public SaveBookRecordResponseDTOAssert hasBookId(Long bookId){
        isNotNull();
        if(!actual.getBookId().equals(bookId)) {
            failWithMessage("Expected book id to be <%s> but was <%s>", bookId, actual.getBookId());
        }
        return this;
    }

    public SaveBookRecordResponseDTOAssert hasPublisher(String publisher){
        isNotNull();
        if(!actual.getPublisher().equals(publisher)) {
            failWithMessage("Expected book publisher to be  <%s> but was <%s>", publisher, actual.getPublisher());
        }
        return this;
    }
}
