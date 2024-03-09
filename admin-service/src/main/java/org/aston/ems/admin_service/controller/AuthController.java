package org.aston.ems.admin_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aston.ems.admin_service.dto.LoginRequest;
import org.aston.ems.admin_service.validation.DtoValidator;
import org.aston.ems.common_lib.exception.web.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/admin/auth", produces = "application/json")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final DtoValidator validator;

    private final Function<LoginRequest,Authentication> reqToAuth = login -> UsernamePasswordAuthenticationToken.unauthenticated(login.username(), login.password());

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, Authentication authentication){
        validator.validate(loginRequest);
        log.debug("/api/v1/auth endpoint was requested");
        authenticationManager.authenticate(reqToAuth.apply(loginRequest));
        return ResponseHandler.generateResponse(HttpStatus.OK, HttpStatus.OK.toString(),String.format("%s was successfully authed",authentication.getName()));
    }

}
