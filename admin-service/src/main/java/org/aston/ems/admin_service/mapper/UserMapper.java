package org.aston.ems.admin_service.mapper;

import org.aston.ems.admin_service.dto.UserDto;
import org.aston.ems.admin_service.model.Authority;
import org.aston.ems.admin_service.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Arrays;
import java.util.Collection;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "username", target = "login")
    @Mapping(source = "password",target = "password")
    @Mapping(source = "authorities",target = "authorities", qualifiedByName = "mapAuthoritiesToArray")
    UserDto toDto(User user);

    @Mapping(source = "login", target = "username")
    @Mapping(source = "password",target = "password")
    @Mapping(source = "authorities",target = "authorities", qualifiedByName = "mapArrayToAuthorities")
    User fromDto(UserDto dto);

    @Named("mapAuthoritiesToArray")
    default String[] mapAuthoritiesToArray(Collection<Authority> authorities) {
        return authorities.stream().map(Authority::getName).toArray(String[]::new);
    }

    @Named("mapArrayToAuthorities")
    default Collection<Authority> mapArrayToAuthorities(String[] arr) {
        return Arrays.stream(arr).map(Authority::new).toList();
    }
}
