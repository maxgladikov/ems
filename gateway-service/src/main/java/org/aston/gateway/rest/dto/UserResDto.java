package org.aston.gateway.rest.dto;


import java.util.Arrays;
import java.util.Objects;


public record UserResDto(String name, String[] authorities) {
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


