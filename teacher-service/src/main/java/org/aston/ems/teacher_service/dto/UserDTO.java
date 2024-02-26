package org.aston.ems.teacher_service.dto;

import lombok.Data;

@Data
public class UserDTO {

    private String name;
    private int mark;

    public UserDTO(){}
    public UserDTO(String name, int mark) {
        this.name = name;
        this.mark = mark;
    }
}
