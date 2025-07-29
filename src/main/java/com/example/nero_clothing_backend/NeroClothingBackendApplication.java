package com.example.nero_clothing_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class NeroClothingBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeroClothingBackendApplication.class, args);
	}

}
