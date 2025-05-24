package com.example.pplite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.pplite")
public class PpliteApplication {

    public static void main(String[] args) {
        SpringApplication.run(PpliteApplication.class, args);
    }

} 