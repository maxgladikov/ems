package org.aston.ems.admin_service.dto;

import jakarta.validation.constraints.Size;

import java.util.Arrays;
import java.util.Objects;


public record UserResDto(@Size(min = 2, message = "Username shall be longer than 1 letters") String name,
                         @Size(min = 1, message = "User shall have lat least one authority")String[] authorities) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResDto userDto = (UserResDto) o;
        return Objects.equals(name, userDto.name) && Arrays.equals(authorities, userDto.authorities);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name);
        result = 31 * result + Arrays.hashCode(authorities);
        return result;
    }
}


