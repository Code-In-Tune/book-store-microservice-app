package com.codeintune.bookstore.testutils.asserts;

import com.codeintune.bookstore.dto.sale.response.GetBookSaleByIdResponseDTO;
import org.assertj.core.api.AbstractAssert;

import java.math.BigDecimal;

public class GetBookSaleByIdResponseDTOAssert extends AbstractAssert<GetBookSaleByIdResponseDTOAssert, GetBookSaleByIdResponseDTO> {


    protected GetBookSaleByIdResponseDTOAssert(GetBookSaleByIdResponseDTO getBookSaleByIdResponseDTO, Class<?> selfType) {
        super(getBookSaleByIdResponseDTO, selfType);
    }

    public static GetBookSaleByIdResponseDTOAssert assertThat(GetBookSaleByIdResponseDTO actual) {
        return new GetBookSaleByIdResponseDTOAssert(actual, GetBookSaleByIdResponseDTOAssert.class);
    }

    public GetBookSaleByIdResponseDTOAssert hasSaleId(Long saleId) {
        isNotNull();
        if(!actual.getSaleId().equals(saleId)) {
            failWithMessage("Expected sale id to be <%s> but was <%s>", saleId, actual.getSaleId());
        }
        return this;
    }

    public GetBookSaleByIdResponseDTOAssert hasBookId(Long bookId){
        isNotNull();
        if(!actual.getBookId().equals(bookId)) {
            failWithMessage("Expected book id to be <%s> but was <%s>", bookId, actual.getBookId());
        }
        return this;
    }
    public GetBookSaleByIdResponseDTOAssert hasAmount(BigDecimal amount){
        isNotNull();
        if(!actual.getAmount().equals(amount)) {
            failWithMessage("Expected amount to be <%s> but was <%s>", amount, actual.getAmount());
        }
        return this;
    }

    public GetBookSaleByIdResponseDTOAssert hasQuantity(Integer quantity){
        isNotNull();
        if(!actual.getQuantity().equals(quantity)) {
            failWithMessage("Expected quantity to be <%s> but was <%s>", quantity, actual.getQuantity());
        }
        return this;
    }
}
