package org.aston.ems.teacher_service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.aston.ems.teacher_service.core.TaskDtoUpdate;
import org.aston.ems.teacher_service.core.TaskDto;
import org.aston.ems.teacher_service.service.api.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path ="/api/v1/teacher", produces = "application/json")
public class TaskController {
	private final ITaskService taskService;

	@Autowired
	public TaskController(ITaskService taskService) {
		this.taskService = taskService;
	}

	@PostMapping
	public ResponseEntity<?> createTask(@RequestBody TaskDto taskDto) {
		taskService.save(taskDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/{teacherName}")
	public ResponseEntity<List<TaskDto>> getAllTeachersTasks(@PathVariable String teacherName) {
		List<TaskDto> tasks = taskService.getAllTeachersTasks(teacherName);
		return new ResponseEntity<> (tasks, HttpStatus.OK);
	}

	@PutMapping("/tasks/{taskId}")
	public ResponseEntity<?> updateTaskAnswer(@PathVariable Long taskId, @RequestBody TaskDtoUpdate request) {
		taskService.updateAnswer(taskId, request.getNickname(), request.getAnswer());
		return ResponseEntity.ok().build();
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody TaskDto taskDto) {
		try {
			taskService.updateMark(taskDto.getId(), taskDto.getMark());
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
