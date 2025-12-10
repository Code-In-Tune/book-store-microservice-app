package com.codeintune.bookstore.specification.impl;

import com.codeintune.bookstore.dto.filter.SearchBookRecordsDTO;
import com.codeintune.bookstore.model.book.BookRecord;
import com.codeintune.bookstore.specification.SpecificationBuilder;
import lombok.NonNull;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class BooksByTitleSpecification implements SpecificationBuilder<SearchBookRecordsDTO, BookRecord> {

    private final String BOOK_FIELD = "title";

    @Override
    public Specification<@NonNull BookRecord> build(SearchBookRecordsDTO filter) {
        return (root, query, cb) -> {
            String title = filter.getTitle();
            if (title == null || title.isBlank()) {
                return cb.conjunction();
            }

            return cb.like(
                    cb.lower(root.get(BOOK_FIELD)),
                    "%" + title.toLowerCase() + "%"
            );
        };
    }
}
