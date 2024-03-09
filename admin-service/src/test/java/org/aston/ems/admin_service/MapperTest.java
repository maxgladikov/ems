package org.aston.ems.admin_service;

import org.aston.ems.admin_service.mapper.UserMapper;
import org.aston.ems.admin_service.model.Authority;
import org.aston.ems.admin_service.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.aston.ems.admin_service.TestFactory.USER;
import static org.aston.ems.admin_service.TestFactory.USER_DTO;

public class MapperTest extends ApplicationTest {

    @Autowired
    UserMapper mapper;

    @Test
    public void shouldReturnUser(){
        User user = mapper.fromDto(USER_DTO);
        Assertions.assertEquals(USER_DTO.login(),user.getUsername());
        Assertions.assertEquals(USER_DTO.password(),user.getPassword());
        Assertions.assertEquals(2,user.getAuthorities().size());
        Assertions.assertTrue(user.getAuthorities().contains(new Authority("ADMIN")));
        Assertions.assertTrue(user.getAuthorities().contains(new Authority("USER")));
    }

    @Test
    public void shouldReturnUserDto(){
        var dto = mapper.toDto(USER);
        Assertions.assertEquals(USER.getUsername(),dto.login());
        Assertions.assertEquals(USER.getPassword(),dto.password());
        Assertions.assertEquals(2,dto.authorities().length);
        Assertions.assertTrue(List.of(dto.authorities()).contains("ADMIN"));
        Assertions.assertTrue(List.of(dto.authorities()).contains("USER"));
    }



}