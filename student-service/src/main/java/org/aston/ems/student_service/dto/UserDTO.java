package org.aston.ems.student_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserDTO {

    String name;

    List<String> authorities;
}
