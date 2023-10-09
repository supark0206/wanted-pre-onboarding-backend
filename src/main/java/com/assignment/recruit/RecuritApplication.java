package com.assignment.recruit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RecuritApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecuritApplication.class, args);
	}

}