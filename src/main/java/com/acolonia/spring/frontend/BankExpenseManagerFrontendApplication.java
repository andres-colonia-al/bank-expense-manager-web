package com.acolonia.spring.frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BankExpenseManagerFrontendApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BankExpenseManagerFrontendApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(BankExpenseManagerFrontendApplication.class, args);
	}

}
