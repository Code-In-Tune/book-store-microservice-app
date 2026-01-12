package com.codeintune.bookstore.testutils.asserts;

import com.codeintune.bookstore.dto.sale.response.SaveBookSaleResponseDTO;
import org.assertj.core.api.AbstractAssert;

import java.math.BigDecimal;

public class SaveBookSaleResponseDTOAssert extends AbstractAssert<SaveBookSaleResponseDTOAssert, SaveBookSaleResponseDTO> {

    protected SaveBookSaleResponseDTOAssert(SaveBookSaleResponseDTO saveBookSaleResponseDTO, Class<?> selfType) {
        super(saveBookSaleResponseDTO, selfType);
    }

    public static SaveBookSaleResponseDTOAssert assertThat(SaveBookSaleResponseDTO actual) {
        return new SaveBookSaleResponseDTOAssert(actual, SaveBookSaleResponseDTOAssert.class);
    }

    public SaveBookSaleResponseDTOAssert hasSaleId(Long saleId){
        isNotNull();
        if(!actual.getSaleId().equals(saleId)) {
            failWithMessage("Expected sale id to be <%s> but was <%s>", saleId, actual.getSaleId());
        }
        return this;
    }

    public SaveBookSaleResponseDTOAssert hasBookId(Long bookId){
        isNotNull();
        if(!actual.getBookId().equals(bookId)) {
            failWithMessage("Expected book id to be <%s> but was <%s>", bookId, actual.getBookId());
        }
        return this;
    }
    public SaveBookSaleResponseDTOAssert hasAmount(BigDecimal amount){
        isNotNull();
        if(!actual.getAmount().equals(amount)) {
            failWithMessage("Expected amount to be <%s> but was <%s>", amount, actual.getAmount());
        }
        return this;
    }

    public SaveBookSaleResponseDTOAssert hasQuantity(Integer quantity){
        isNotNull();
        if(!actual.getQuantity().equals(quantity)) {
            failWithMessage("Expected quantity to be <%s> but was <%s>", quantity, actual.getQuantity());
        }
        return this;
    }

}
