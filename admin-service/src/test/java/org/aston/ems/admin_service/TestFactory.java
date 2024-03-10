package org.aston.ems.admin_service;

import org.aston.ems.admin_service.dto.UserReqDto;
import org.aston.ems.admin_service.dto.UserResDto;
import org.aston.ems.admin_service.model.Authority;
import org.aston.ems.admin_service.model.User;
import org.aston.ems.admin_service.security.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class TestFactory {
    private static final String USERNAME = "max";
    private static final String PASSWORD = "max";
    private static final String[] AUTHORITIES = {"ADMIN"};

    public static final UserResDto USER_RES_DTO = new UserResDto(USERNAME,AUTHORITIES);
    public static final UserReqDto USER_REQ_DTO = new UserReqDto(USERNAME, PASSWORD, AUTHORITIES);
    public static final UserReqDto USER_REQ_NEW_DTO = new UserReqDto("USERNAME", "PASSWORD", AUTHORITIES);

    public static final User USER = User.builder()
        .username(USERNAME)
        .authorities(getAuthorities())
        .build();

    public static final User USER_TWO = User.builder()
        .username(USERNAME+"_two")
        .authorities(getAuthorities())
        .build();

    public static final List<User> USERS = List.of(USER,USER_TWO);

    public static final UserDetails USER_DETAILS = SecurityUser.from(USER);

    private static Collection<Authority> getAuthorities(){
        return Arrays.stream(AUTHORITIES)
            .map(Authority::new)
            .toList();
    }

}
