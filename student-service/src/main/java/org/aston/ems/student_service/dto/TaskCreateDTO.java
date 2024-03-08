package org.aston.ems.student_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TaskCreateDTO {

    @NotNull
    private Long id;

    @NotNull
    private Long assigneeId;

    @NotBlank
    private String content;
}
