package org.aston.ems.admin_service;

import lombok.RequiredArgsConstructor;
import org.aston.ems.admin_service.dto.UserDto;
import org.aston.ems.admin_service.mapper.UserMapper;
import org.aston.ems.admin_service.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class Application implements CommandLineRunner {


	private final UserService service;

	private final UserMapper mapper;
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		}

	@Override
	public void run(String... args) throws Exception {

	service.create(mapper.fromDto(new UserDto("max","secret",new String[]{"ADMIN"})));
	}
}
