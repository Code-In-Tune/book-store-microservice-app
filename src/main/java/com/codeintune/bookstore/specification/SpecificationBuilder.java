package com.codeintune.bookstore.specification;

import com.codeintune.bookstore.dto.filter.SearchBookRecordsDTO;
import lombok.NonNull;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<@NonNull T, @NonNull E> {

    Specification<@NonNull E> build(SearchBookRecordsDTO filter);
}
