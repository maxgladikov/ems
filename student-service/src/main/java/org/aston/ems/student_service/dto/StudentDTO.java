package org.aston.ems.student_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class StudentDTO {

    private Long id;

    private String nickname;
}
