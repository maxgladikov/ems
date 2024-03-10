package org.aston.ems.teacher_service.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor(onConstructor_ = @JsonCreator)
@NoArgsConstructor
@Getter
@Setter
public class RequestTaskDtoCreate {
    private Long id;
    private String nickName;
    private String content;
}
