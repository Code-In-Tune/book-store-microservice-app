package com.codeintune.bookstore.model.sale;

import com.codeintune.bookstore.model.book.BookRecord;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book_sale")
public class BookSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id", nullable = false)
    private Long saleId;
    @Column(name = "book_id", nullable = false)
    private Long bookId;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    @Column(name = "date_sold", nullable = false)
    private Instant dateSold;
    @Column(name = "amount", nullable = false, scale = 2, precision = 12)
    private BigDecimal amount;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false, insertable = false, updatable = false)
    private BookRecord bookRecord;
}
