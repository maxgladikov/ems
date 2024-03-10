package org.aston.ems.student_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentUpdateDTO {
    @NotNull
    private String nickname;
}
