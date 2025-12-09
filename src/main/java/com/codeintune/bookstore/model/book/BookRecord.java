package com.codeintune.bookstore.model.book;

import com.codeintune.bookstore.model.book.converter.AvailabilityAttributeConverter;
import com.codeintune.bookstore.model.book.enums.Availability;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book_record")
public class BookRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id",  nullable = false)
    private Long bookId;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "author", nullable = false)
    private String author;
    @Column(name = "price", nullable = false, scale = 2, precision = 12)
    private BigDecimal price;
    @Column(name = "isbn", nullable = false)
    private String isbn;
    @Column(name = "publisher", nullable = false)
    private String publisher;
    @Column(name = "availability", nullable = false)
    @Convert(converter = AvailabilityAttributeConverter.class)
    private Availability availability;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

}
