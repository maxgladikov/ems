package org.aston.ems.admin_service;

import org.aston.ems.admin_service.dto.UserDto;
import org.aston.ems.admin_service.model.Authority;
import org.aston.ems.admin_service.model.User;
import org.aston.ems.admin_service.security.SecurityUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class TestFactory {
    private static final String USERNAME = "test";
    private static final String PASSWORD = "password";
    private static final String[] AUTHORITIES = {"ADMIN","USER"};

    public static final UserDto USER_DTO = new UserDto(USERNAME,PASSWORD,AUTHORITIES);

    public static final User USER = User.builder()
        .username(USERNAME)
        .password(PASSWORD)
        .authorities(getAuthorities())
        .build();

    public static final UserDetails USER_DETAILS = SecurityUser.from(USER);

    private static Collection<Authority> getAuthorities(){
        return Arrays.stream(AUTHORITIES)
            .map(Authority::new)
            .toList();
    }

}
