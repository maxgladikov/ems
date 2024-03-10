package org.aston.ems.admin_service.mapper;

import org.aston.ems.admin_service.ApplicationTest;
import org.aston.ems.admin_service.model.Authority;
import org.aston.ems.admin_service.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.aston.ems.admin_service.TestFactory.USER;
import static org.aston.ems.admin_service.TestFactory.USER_RES_DTO;
import static org.aston.ems.admin_service.TestFactory.USER_REQ_DTO;

public class MapperTest extends ApplicationTest {

    @Autowired
    UserMapper mapper;

    @Test
    @Disabled
    public void fromUserResDto_shouldReturnUser(){
        User user = mapper.fromResDto(USER_RES_DTO);
        Assertions.assertEquals(USER_RES_DTO.name(),user.getUsername());
        Assertions.assertEquals(1,user.getAuthorities().size());
        Assertions.assertTrue(user.getAuthorities().contains(new Authority("ADMIN")));
    }

    @Test
    @Disabled
    public void fromUserReqDto_shouldReturnUser(){
        User user = mapper.fromReqDto(USER_REQ_DTO);
        Assertions.assertEquals(USER_REQ_DTO.login(),user.getUsername());
        Assertions.assertEquals(USER_REQ_DTO.password(),user.getPassword());
        Assertions.assertEquals(1,user.getAuthorities().size());
        Assertions.assertTrue(user.getAuthorities().contains(new Authority("ADMIN")));
    }

    @Test
    @Disabled
    public void shouldReturnUserResDto(){
        var dto = mapper.toResDto(USER);
        Assertions.assertEquals(USER.getUsername(),dto.name());
        Assertions.assertEquals(1,dto.authorities().length);
        Assertions.assertTrue(List.of(dto.authorities()).contains("ADMIN"));
    }

    @Test
    @Disabled
    public void shouldReturnUserReqDto(){
        var dto = mapper.toReqDto(USER);
        Assertions.assertEquals(USER.getUsername(),dto.login());
        Assertions.assertEquals(USER.getPassword(),dto.password());
        Assertions.assertEquals(1,dto.authorities().length);
        Assertions.assertTrue(List.of(dto.authorities()).contains("ADMIN"));
    }



}