package org.aston.ems.admin_service;

import org.aston.ems.common_lib.exception.web.EmsResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		new EmsResponse<String>();
	}
}
