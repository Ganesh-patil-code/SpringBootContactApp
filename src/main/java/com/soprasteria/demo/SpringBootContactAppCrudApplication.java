package com.soprasteria.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.soprasteria.dao")
@EntityScan("com.soprasteria.model")
@SpringBootApplication(scanBasePackages = "com.soprasteria")
public class SpringBootContactAppCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootContactAppCrudApplication.class, args);
		System.out.println("hello this is second spring boot crud example");
	}

}
