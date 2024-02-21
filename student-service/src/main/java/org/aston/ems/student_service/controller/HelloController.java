package org.aston.ems.student_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/", produces = "application/json")
public class HelloController {
	
	@GetMapping
	ResponseEntity<String>  doGreet(){
		return ResponseEntity.ok()
				.header("Access-Control-Allow-Origin", "*")
				.body("Hello!");
	}
}
