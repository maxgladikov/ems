package org.aston.ems.teacher_service.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TeacherDtoResp {
    private String name;
    private String[] authorities;
}
