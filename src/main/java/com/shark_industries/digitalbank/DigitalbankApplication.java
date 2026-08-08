package com.shark_industries.digitalbank;

import com.shark_industries.digitalbank.authservice.services.Registration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DigitalbankApplication {


	public static void main(String[] args) {
		SpringApplication.run(DigitalbankApplication.class, args);

		Registration registration = new Registration();

		registration.register();


	}



}
