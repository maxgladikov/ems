package org.aston.ems.admin_service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest
@ActiveProfiles("test")
public class ApplicationTest {

    static PostgreSQLContainer<?> dataSource = new PostgreSQLContainer<>(
        "postgres:16-alpine"
    );

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", dataSource::getJdbcUrl);
        registry.add("spring.datasource.username", dataSource::getUsername);
        registry.add("spring.datasource.password", dataSource::getPassword);
    }

    @BeforeAll
    static void setUp(){
        dataSource.start();
    }

    @AfterAll
    static void tearDown(){
        dataSource.stop();
    }


}
