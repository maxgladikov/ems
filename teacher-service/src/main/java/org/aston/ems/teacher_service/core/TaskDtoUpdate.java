package org.aston.ems.teacher_service.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.*;

@AllArgsConstructor(onConstructor_ = @JsonCreator)
@NoArgsConstructor
@Getter
@Setter
public class TaskDtoUpdate {
    private long id;
    private long studentId;
    private String answer;
    private int mark;

    @Builder
    public TaskDtoUpdate(long id, long studentId, String answer) {
        this.id = id;
        this.studentId = studentId;
        this.answer = answer;
    }

    @Builder
    public TaskDtoUpdate(long id, long studentId, int mark) {
        this.id = id;
        this.studentId = studentId;
        this.mark = mark;
    }
}
