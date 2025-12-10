package com.codeintune.bookstore.repository;

import com.codeintune.bookstore.model.book.BookRecord;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRecordRepository extends JpaRepository<@NonNull BookRecord, @NonNull Long>, JpaSpecificationExecutor<@NonNull BookRecord> {
}
