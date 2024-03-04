package org.aston.ems.student_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
public class AnswerData implements Serializable {

    private Long id;

    private Long studentId;

    private String answer;
}
