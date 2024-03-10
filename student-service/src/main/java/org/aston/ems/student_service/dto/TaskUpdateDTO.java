package org.aston.ems.student_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.aston.ems.student_service.model.Score;
import org.openapitools.jackson.nullable.JsonNullable;

@Setter
@Getter
public class TaskUpdateDTO {

    @NotNull
    private Long id;

    @NotNull
    private String nickname;

    private JsonNullable<String> answer;

    private JsonNullable<Score> mark;
}
