package org.aston.ems.admin_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.aston.ems.admin_service.dto.LoginRequest;
import org.aston.ems.admin_service.repository.AuthorityRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class IntegrationTest extends ApplicationTest{

    @Autowired
    private  AuthorityRepository authorityRepository;
    @Test
    @Sql("classpath:/sql/clear_data.sql")
    @Sql("classpath:/sql/data.sql")
    void test(){
        authorityRepository.findAll().stream().forEach(x -> System.out.println(x.getId()+x.getName()));
    }




}
