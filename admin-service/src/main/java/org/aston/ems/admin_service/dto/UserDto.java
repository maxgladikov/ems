package org.aston.ems.admin_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDto(@Size(min = 2, message = "Username shall be longer than 1 letters") String login,
                    @Size(min = 5, message = "Password shall be longer than 4 letters") String password,
                    @Size(min = 1, message = "User shall have lat least one authority")String[] authorities) {}
