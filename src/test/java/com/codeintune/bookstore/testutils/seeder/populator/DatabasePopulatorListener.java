package com.codeintune.bookstore.testutils.seeder.populator;

import com.codeintune.bookstore.testutils.testcontainers.AbstractContainerBaseTest;
import lombok.NonNull;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.sql.Connection;

public class DatabasePopulatorListener implements ApplicationListener<@NonNull ContextRefreshedEvent> {


    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {
        DataSource dataSource = event.getApplicationContext().getBean(DataSource.class);
        try(Connection connection = dataSource.getConnection()) {
            ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
            Resource bookRecordTable = new ClassPathResource("db/01_book_record_table.sql");
            Resource bookSaleTable = new ClassPathResource("db/02_book_sale_table.sql");
            Resource bookRecordConstraints = new ClassPathResource("db/03_book_record_constraint.sql");
            Resource bookSaleConstraints = new ClassPathResource("db/04_book_sale_constraint.sql");
            if(!AbstractContainerBaseTest.isPopulated) {
                databasePopulator.addScripts(bookRecordTable, bookSaleTable, bookRecordConstraints, bookSaleConstraints);
                databasePopulator.populate(connection);
                AbstractContainerBaseTest.isPopulated = true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
