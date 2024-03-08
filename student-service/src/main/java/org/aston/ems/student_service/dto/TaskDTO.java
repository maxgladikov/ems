package org.aston.ems.student_service.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TaskDTO {

    private Long id;
    private Long assigneeId;
    private String content;
    private String answer;
    private int mark;
}
