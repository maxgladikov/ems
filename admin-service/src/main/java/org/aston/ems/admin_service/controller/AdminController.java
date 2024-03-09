package org.aston.ems.admin_service.controller;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aston.ems.admin_service.dto.UserDto;
import org.aston.ems.admin_service.mapper.UserMapper;
import org.aston.ems.admin_service.service.UserService;
import org.aston.ems.admin_service.validation.DtoValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/admin/users", produces = "application/json")
@Validated
public class AdminController {

	private final UserService service;

	private final UserMapper mapper;
	private final DtoValidator validator;

	@GetMapping
	public List<UserDto> get(){
		return service.get().stream().map(mapper::toDto).toList();
	}

	@GetMapping("/{username}")
	public UserDto getAll(@PathVariable @NotBlank String username){
		 return mapper.toDto(service.get(username));
	}

	@PostMapping()
	public ResponseEntity<?> create(@RequestBody UserDto newUser){
		validator.validate(newUser);
		service.create(mapper.fromDto(newUser));
		 return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping()
	public void update(@RequestBody UserDto newUser){
		validator.validate(newUser);
		service.update(mapper.fromDto(newUser));
	}
	@DeleteMapping()
		public void delete(@PathVariable String username){
		service.delete(username);
	}

}
