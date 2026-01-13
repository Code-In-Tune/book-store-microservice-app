package com.codeintune.bookstore.integration.specification;

import com.codeintune.bookstore.dto.filter.SearchBookRecordsDTO;
import com.codeintune.bookstore.model.book.BookRecord;
import com.codeintune.bookstore.repository.BookRecordRepository;
import com.codeintune.bookstore.specification.impl.BooksByPublisherSpecification;
import com.codeintune.bookstore.testutils.seeder.populator.DatabasePopulatorListener;
import com.codeintune.bookstore.testutils.testcontainers.AbstractContainerBaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

@Import({BooksByPublisherSpecification.class, DatabasePopulatorListener.class})
@DataJpaTest(properties = {
        "spring.jpa.hibernate.ddl-auto=none"
})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BooksByPublisherSpecificationBuilderIT extends AbstractContainerBaseTest {

    @Autowired
    private BookRecordRepository bookRecordRepository;
    @Autowired
    private BooksByPublisherSpecification booksByPublisherSpecification;

    @Test
    @Sql("classpath:db/integration_test_specification/books_insert.sql")
    @Rollback
    void testFound(){
        SearchBookRecordsDTO searchByPublisher = new SearchBookRecordsDTO();
        searchByPublisher.setPublisher("Harper");
        Long recordFoundCount = bookRecordRepository.findAll(booksByPublisherSpecification.build(searchByPublisher))
                .stream().map(BookRecord::getPublisher).filter("Harper"::equals).count();
        Assertions.assertEquals(1,recordFoundCount);
    }

    @Test
    @Sql("classpath:db/integration_test_specification/books_insert.sql")
    @Rollback
    void testNotFound(){
        SearchBookRecordsDTO searchByPublisher = new SearchBookRecordsDTO();
        searchByPublisher.setTitle("Einaudi");
        Long recordFoundCount = bookRecordRepository.findAll(booksByPublisherSpecification.build(searchByPublisher))
                .stream().map(BookRecord::getTitle).filter("Einaudi"::equals).count();
        Assertions.assertEquals(0,recordFoundCount);
    }
}
