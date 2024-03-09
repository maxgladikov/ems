package org.aston.ems.admin_service.dto;

import jakarta.validation.constraints.Size;

public record LoginRequest(@Size(min = 2, message = "Username shall be longer than 1 letters") String username,
                           @Size(min = 5, message = "Password shall be longer than 4 letters") String password) {}