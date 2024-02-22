package org.aston.ems.admin_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/", produces = "application/json")
public class HelloController {
	
	@GetMapping
	ResponseEntity<String>  doGreet(){
		log.debug("/api/v1 endpoint was requested");
		return ResponseEntity.ok()
				.header("Access-Control-Allow-Origin", "*")
				.body("Hello!");
	}
}
