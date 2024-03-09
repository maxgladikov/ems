package org.aston.ems.teacher_service.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@AllArgsConstructor(onConstructor_ = @JsonCreator)
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskDtoUpdate {
    private Long id;
    private Long assigneeId;
    private Long studentId;
    private String answer;
    private int mark;

    @Builder
    public TaskDtoUpdate(Long id, Long studentId, String answer) {
        this.id = id;
        this.studentId = studentId;
        this.answer = answer;
    }

    @Builder
    public TaskDtoUpdate(Long id, Long assigneeId, int mark) {
        this.id = id;
        this.assigneeId = assigneeId;
        this.mark = mark;
    }
}
