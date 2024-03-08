package org.aston.ems.student_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.aston.ems.student_service.dto.StudentCreateDTO;
import org.aston.ems.student_service.dto.StudentDTO;
import org.aston.ems.student_service.dto.StudentUpdateDTO;
import org.aston.ems.student_service.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Tag(name = "Student controller", description = "Interaction with students")
@RestController
@RequestMapping("/api/v1/students")
@AllArgsConstructor
public class StudentsController {

    public static final String ID = "/{id}";

    private final StudentService studentService;

    @Operation(summary = "Get specific student by his id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the student",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Student with that id not found",
                    content = @Content) })
    @GetMapping(ID)
    @ResponseStatus(HttpStatus.OK)
    public StudentDTO show(
            @Parameter(description = "Id of student to be searched")
            @PathVariable Long id) {
        return studentService.findById(id);
    }

//    @Operation(summary = "Get list of all students")
//    @ApiResponse(responseCode = "200", description = "List of all students",
//            content = { @Content(mediaType = "application/json",
//                    schema = @Schema(implementation = StudentDTO.class)) })
//    @GetMapping("")
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<List<StudentDTO>> index() {
//        var students = studentService.getAll();
//        return ResponseEntity.ok()
//                .header("X-Total-Count", String.valueOf(students.size()))
//                .body(students);
//    }

    @Operation(summary = "Create new student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid student data supplied",
                    content = @Content) })
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDTO create(
            @Parameter(description = "Student data to save")
            @Valid @RequestBody final StudentCreateDTO studentData) {
        return studentService.create(studentData);
    }

//    @Operation(summary = "Update student by his id")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Student updated",
//                    content = { @Content(mediaType = "application/json",
//                            schema = @Schema(implementation = StudentDTO.class)) }),
//            @ApiResponse(responseCode = "400", description = "Invalid student data supplied", content = @Content),
//            @ApiResponse(responseCode = "404", description = "Student with that id not found", content = @Content)
//    })
//    @PutMapping(ID)
//    @ResponseStatus(HttpStatus.OK)
//    public StudentDTO update(
//            @Parameter(description = "Student data to update")
//            @RequestBody @Valid StudentUpdateDTO studentData,
//            @Parameter(description = "Id of student to be updated")
//            @PathVariable Long id) {
//        return studentService.update(studentData, id);
//    }

//    @Operation(summary = "Delete student by his id")
//    @ApiResponse(responseCode = "204", description = "Student deleted", content = @Content)
//    @DeleteMapping(ID)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void destroy(
//            @Parameter(description = "Id of student to be deleted")
//            @PathVariable Long id) {
//        studentService.delete(id);
//    }
}
