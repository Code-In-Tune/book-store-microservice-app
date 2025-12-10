package com.codeintune.bookstore.repository;

import com.codeintune.bookstore.model.sale.BookSale;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BookSaleRepository extends JpaRepository<@NonNull BookSale, @NonNull Long>, JpaSpecificationExecutor<@NonNull BookSale> {
}
