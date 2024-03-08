package org.aston.ems.user_service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class StudentProgressDataDTO {

    private Long id;

    private String nickname;

    private List<TaskDTO> tasks;
}
