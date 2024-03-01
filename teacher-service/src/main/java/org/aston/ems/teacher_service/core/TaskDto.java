package org.aston.ems.teacher_service.core;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private long id;
    private long studentId;
    private long teacherId;
    private String task;
    private boolean checked;
    private int mark;
}
