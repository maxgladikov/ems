package org.aston.ems.student_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class StudentProgressDataDTO {

    private Long id;

    private String nickname;

    private List<TaskDTO> tasks;
}
