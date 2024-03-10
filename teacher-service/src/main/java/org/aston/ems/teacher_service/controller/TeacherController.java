package org.aston.ems.teacher_service.controller;

import org.aston.ems.teacher_service.core.TeacherDto;
import org.aston.ems.teacher_service.service.api.ITaskService;
import org.aston.ems.teacher_service.service.api.ITeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path ="/api/v1/teacher/create", produces = "application/json")
public class TeacherController {
    private final ITeacherService teacherService;

    public TeacherController(ITeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public ResponseEntity<?> createTeacher(@RequestBody TeacherDto teacherDto) {
        teacherService.createTeacher(teacherDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
