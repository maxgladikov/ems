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
    private String nickname;
    private String answer;
    private int mark;

    @Builder
    public TaskDtoUpdate(Long id, String nickname, String answer) {
        this.id = id;
        this.nickname = nickname;
        this.answer = answer;
    }

    @Builder
    public TaskDtoUpdate(Long id, String nickname, int mark) {
        this.id = id;
        this.nickname = nickname;
        this.mark = mark;
    }
}
