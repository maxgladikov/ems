package org.aston.gateway;

import lombok.extern.slf4j.Slf4j;
import org.aston.gateway.rest.client.AuthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@Slf4j
public class Application implements CommandLineRunner {
	@Autowired
	private AuthClient client;
	public static void main(String[] args) {
			SpringApplication.run(Application.class, args);
		}


	@Override
	public void run(String... args) throws Exception {
		client.getUserByUsername("admin");
		log.info("******************************");
		log.info("feign works:{}",client.getUserByUsername("admin").toString());
		log.info("******************************");
	}
}
