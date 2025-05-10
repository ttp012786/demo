package com.example.demo.config;

import com.example.demo.LocalDemoApplication;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@ActiveProfiles("test")
@Testcontainers
@SpringBootTest(classes = LocalDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Ensures the container is shared across multiple classes
public class JUnitTestcontainersConfiguration {

    @Autowired
    private Flyway flyway;

    @LocalServerPort
    protected int port;

    protected String baseUrl;
    protected TestRestTemplate restTemplate;
    protected HttpHeaders headers;

    @Container
    public static final PostgreSQLContainer<?> POSTGRES_CONTAINER;

    static {
        POSTGRES_CONTAINER = new PostgreSQLContainer<>("postgres:15")
                .withDatabaseName("testdb")
                .withUsername("testuser")
                .withPassword("testpass")
                .waitingFor(Wait.forListeningPort())
                .withStartupTimeout(Duration.of(30, ChronoUnit.SECONDS));
        POSTGRES_CONTAINER.start();
    }

    @Bean
    public DataSource testDataSource() {
        return DataSourceBuilder.create()
                .url(POSTGRES_CONTAINER.getJdbcUrl())
                .username(POSTGRES_CONTAINER.getUsername())
                .password(POSTGRES_CONTAINER.getPassword())
                .build();
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.r2dbc.url", () ->
                "r2dbc:postgresql://" + POSTGRES_CONTAINER.getHost() + ":" +
                        POSTGRES_CONTAINER.getFirstMappedPort() + "/" + POSTGRES_CONTAINER.getDatabaseName());
        registry.add("spring.r2dbc.username", POSTGRES_CONTAINER::getUsername);
        registry.add("spring.r2dbc.password", POSTGRES_CONTAINER::getPassword);
    }

    @BeforeEach
    void setup() {
        flyway.migrate(); // Run Flyway before tests
        baseUrl = "http://localhost:" + port;
        restTemplate = new TestRestTemplate();
        headers = new HttpHeaders();
    }
}
