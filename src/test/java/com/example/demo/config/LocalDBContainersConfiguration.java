package com.example.demo.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.HostPortWaitStrategy;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class LocalDBContainersConfiguration {
    @Bean
    @ServiceConnection(name = "postgreSQLContainer")
    public PostgreSQLContainer<?> postgreSQLContainer() {
        PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"))
                .withDatabaseName("testdb")
                .withUsername("testuser")
                .withPassword("testpass");

        postgreSQLContainer.start();
        postgreSQLContainer.setWaitStrategy(new HostPortWaitStrategy());

        return postgreSQLContainer;
    }
}