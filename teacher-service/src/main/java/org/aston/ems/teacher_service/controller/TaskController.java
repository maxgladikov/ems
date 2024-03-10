package org.aston.ems.teacher_service.controller;

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

	@GetMapping("/{teacherId}")
	public ResponseEntity<List<TaskDto>> getAllTeachersTasks(@PathVariable Long teacherId) {
		List<TaskDto> tasks = taskService.getAllTeachersTasks(teacherId);
		return new ResponseEntity<> (tasks, HttpStatus.OK);
	}

	@PutMapping("/tasks/{taskId}")
	public ResponseEntity<?> updateTaskAnswer(@PathVariable Long taskId, @RequestBody TaskDtoUpdate request) {
		taskService.updateAnswer(taskId, request.getNickName(), request.getAnswer());
		return ResponseEntity.ok().build();
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody TaskDto taskDto) {
		taskService.updateMark(taskDto.getId(), taskDto.getMark());
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@DeleteMapping("/{taskId}")
	public ResponseEntity<?> delete(@PathVariable Long taskId) {
		taskService.delete(taskId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
