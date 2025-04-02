package com.example.demo;

import com.example.demo.config.DBTestConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(DBTestConfig.class)
public class LocalDemoApplication {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "test"); // Force test profile
        SpringApplication.from(DemoApplication::main)
                .run(args);
    }
}
