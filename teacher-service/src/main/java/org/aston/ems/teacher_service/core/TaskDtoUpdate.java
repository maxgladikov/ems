package org.aston.ems.teacher_service.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.*;

@AllArgsConstructor(onConstructor_ = @JsonCreator)
@NoArgsConstructor
@Getter
@Setter
public class TaskDtoUpdate {
    private Long id;
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
    public TaskDtoUpdate(Long id, Long studentId, int mark) {
        this.id = id;
        this.studentId = studentId;
        this.mark = mark;
    }
}
