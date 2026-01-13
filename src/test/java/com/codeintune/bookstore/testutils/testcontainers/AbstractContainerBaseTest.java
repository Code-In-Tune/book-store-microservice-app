package com.codeintune.bookstore.testutils.testcontainers;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.postgresql.PostgreSQLContainer;

public class AbstractContainerBaseTest {

    public static final PostgreSQLContainer POSTGRE_SQL_CONTAINER;
    public static boolean isPopulated;
    static {
        POSTGRE_SQL_CONTAINER = new PostgreSQLContainer("postgres:latest");
        if(!POSTGRE_SQL_CONTAINER.isRunning()) {
            POSTGRE_SQL_CONTAINER.start();
        }


    }

    @DynamicPropertySource
    static void dynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRE_SQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRE_SQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", POSTGRE_SQL_CONTAINER::getPassword);
    }
}
