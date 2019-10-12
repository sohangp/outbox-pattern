package com.sohan.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The spring boot application class, that starts the app.
 */
@SpringBootApplication
public class StudentApplication {

	/**
	 * Main method that starts the Spring Boot application.
	 *
	 * @param args Arguments passed to the app.
	 */
	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}
}
