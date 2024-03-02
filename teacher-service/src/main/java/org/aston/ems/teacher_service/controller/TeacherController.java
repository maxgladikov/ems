package org.aston.ems.teacher_service.controller;

import org.aston.ems.teacher_service.core.TaskDto;
import org.aston.ems.teacher_service.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = {"/api/v1/teacher"}, produces = {"application/json"})
public class TeacherController {
	private final ITaskService taskService;

	@Autowired
	public TeacherController(ITaskService taskService) {
		this.taskService = taskService;
	}

	@RequestMapping(
			method = {RequestMethod.POST}
	)
	public ResponseEntity<?> createTask(@RequestBody TaskDto taskDto) {
		this.taskService.save(taskDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
