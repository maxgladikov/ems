package org.aston.ems.user_service.controller;

import org.aston.ems.user_service.dto.UserDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(path = "/api/v1/info", produces = "application/json")
public class InfoStudentController {

    @GetMapping(value = "/student",produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getInfoStudent(@RequestParam String name) {

        if (name.equals("student")) return new UserDTO("student",100);

        else  throw new RuntimeException("Not found a student");
    }
}


