package com.example.demo.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

import static com.example.demo.config.JUnitTestcontainersConfiguration.POSTGRES_CONTAINER;

@TestConfiguration
public class DBTestConfig {

    @Bean
    public DataSource testDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(POSTGRES_CONTAINER.getJdbcUrl());
        hikariConfig.setUsername(POSTGRES_CONTAINER.getUsername());
        hikariConfig.setPassword(POSTGRES_CONTAINER.getPassword());
        hikariConfig.setDriverClassName("org.postgresql.Driver");

        return new HikariDataSource(hikariConfig);
    }
}
