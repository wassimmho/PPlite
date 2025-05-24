package com.PPlite.pplite_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.PPlite.pplite_backend")
public class PpliteApplication {

	public static void main(String[] args) {
		SpringApplication.run(PpliteApplication.class, args);
	}

}
