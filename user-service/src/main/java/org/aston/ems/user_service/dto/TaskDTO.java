package org.aston.ems.user_service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class TaskDTO {

    private Long id;
    private Long assigneeId;
    private String content;
    private String answer;
    private int mark;
}
