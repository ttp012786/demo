package com.example.config;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public class TestcontainersConfiguration {

    // Define a PostgreSQL Testcontainer instance
    private static final PostgreSQLContainer<?> POSTGRESQL_CONTAINER;
    static {
        POSTGRESQL_CONTAINER = new PostgreSQLContainer<>(DockerImageName.parse("postgres:15-alpine"))
                .withDatabaseName("testdb")
                .withUsername("testuser")
                .withPassword("testpass")
                .withReuse(true);

        POSTGRESQL_CONTAINER.start();
        Runtime.getRuntime().addShutdownHook(new Thread(POSTGRESQL_CONTAINER::stop)); // Ensures cleanup
    }

    // Start the container
    static {
        POSTGRESQL_CONTAINER.start();
    }

    /**
     * Registers the container properties dynamically in Spring's environment.
     */
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        final String dbUrl = String.format("r2dbc:postgresql://%s:%d/%s",
                POSTGRESQL_CONTAINER.getHost(),
                POSTGRESQL_CONTAINER.getMappedPort(PostgreSQLContainer.POSTGRESQL_PORT),
                POSTGRESQL_CONTAINER.getDatabaseName());
        registry.add("spring.r2dbc.url", () -> dbUrl);
        registry.add("spring.r2dbc.username", POSTGRESQL_CONTAINER::getUsername);
        registry.add("spring.r2dbc.password", POSTGRESQL_CONTAINER::getPassword);
    }
}
