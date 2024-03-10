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
    private String nickName;
    private String answer;
    private int mark;

    @Builder
    public TaskDtoUpdate(Long id, String nickName, String answer) {
        this.id = id;
        this.nickName = nickName;
        this.answer = answer;
    }

    @Builder
    public TaskDtoUpdate(Long id, String nickName, int mark) {
        this.id = id;
        this.nickName = nickName;
        this.mark = mark;
    }
}
