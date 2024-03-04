package org.aston.ems.student_service.controller.students;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.aston.ems.student_service.dto.TaskCreateDTO;
import org.aston.ems.student_service.dto.TaskDTO;
import org.aston.ems.student_service.dto.TaskUpdateDTO;
import org.aston.ems.student_service.model.AnswerData;
import org.aston.ems.student_service.service.TaskService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Tag(name = "Student tasks controller", description = "Interaction with student tasks")
@RestController
@RequestMapping("/api/v1/students/{studentId}/tasks")
@AllArgsConstructor
public class TasksController {

    public static final String ID = "/{id}";

    private final TaskService taskService;

    @Operation(summary = "Get a task by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the task",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Task with that id not found",
                    content = @Content) })
    @GetMapping(ID)
    @ResponseStatus(HttpStatus.OK)
    public TaskDTO show(
            @Parameter(description = "Id of task to be searched")
            @PathVariable Long id) {
        return taskService.findById(id);
    }

    @Operation(summary = "Get list of all student tasks")
    @ApiResponse(responseCode = "200", description = "List of all student tasks",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = TaskDTO.class)) })
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<TaskDTO>> index(
            @Parameter(description = "The student id that will be used to find all his tasks")
            @PathVariable Long studentId) {
        var tasks = taskService.getAll(studentId);
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(tasks.size()))
                .body(tasks);
    }

    @Operation(summary = "Create new task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid task data supplied",
                    content = @Content) })
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO create(
            @Parameter(description = "Task data to save")
            @Valid @RequestBody TaskCreateDTO taskData) {
        return taskService.create(taskData);
    }

    @Operation(summary = "Update task by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid task data supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Task with that id not found", content = @Content)
    })
    @PutMapping(ID)
    @ResponseStatus(HttpStatus.OK)
    public TaskDTO update(
            @Parameter(description = "The student id that will be used to update his task")
            @PathVariable Long studentId,
            @Parameter(description = "Task data to update")
            @RequestBody @Valid TaskUpdateDTO taskData,
            @Parameter(description = "Id of task to be updated")
            @PathVariable Long id) {

        var answer = taskData.getAnswer();

        if (answer != null) {
            var data = new AnswerData(id, studentId, answer.get());

            RestTemplate restTemplate = new RestTemplate();
            String resourceUrl = "http://localhost:9002/api/v1/teacher/tasks/" + id;
            HttpEntity<AnswerData> requestUpdate = new HttpEntity<>(data);
            restTemplate.exchange(resourceUrl, HttpMethod.PUT, requestUpdate, Void.class);
        }

        return taskService.update(taskData, id);
    }

//    @Operation(summary = "Delete task by its id")
//    @ApiResponse(responseCode = "204", description = "Task deleted", content = @Content)
//    @DeleteMapping(ID)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void destroy(
//            @Parameter(description = "Id of task to be deleted")
//            @PathVariable Long id) {
//        taskService.delete(id);
//    }
}
