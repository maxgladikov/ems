package org.aston.ems.student_service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@OpenAPIDefinition(
		info = @Info(
				title = "Student Service",
				version = "1.0",
				description = "Student management system",
				contact = @Contact(
						name = "Darya Koval",
						url = "https://github.com/dariakoval"
				)
		)
)
@SpringBootApplication
@EnableJpaAuditing
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
