package org.aston.ems.admin_service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aston.ems.admin_service.dto.UserReqDto;
import org.aston.ems.admin_service.mapper.UserMapper;
import org.aston.ems.admin_service.service.UserService;
import org.aston.ems.admin_service.util.Population;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executors;
import java.util.function.Function;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class Application implements CommandLineRunner {


	private final UserService service;

	private final UserMapper mapper;
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		}

	@Override
	public void run(String... args) throws Exception {

		Function<UserReqDto,Runnable> task = req -> new Runnable() {
			@Override
			public void run() {
				try {
					service.create(mapper.fromReqDto(req));
				} catch (Throwable e){
					log.info("Suppose this user already in db");
				}
			}
		};

		var executor = Executors.newCachedThreadPool();
		Population.USERS.stream().map(task::apply).forEach(executor::submit);
	}
}
