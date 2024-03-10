package org.aston.ems.admin_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aston.ems.admin_service.ApplicationTest;
import org.aston.ems.admin_service.dto.LoginRequest;
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


@AutoConfigureMockMvc
class AuthControllerTest extends ApplicationTest {

    @Autowired
    private MockMvc mockMvc;


    @Nested
    class Login{
        String url = "/api/v1/admin/auth";




        @Test
        @Sql("classpath:/sql/clear_data.sql")
        @Sql("classpath:/sql/data.sql")
        void shouldReturn200() throws Exception{
            LoginRequest dto = new LoginRequest("max","max");
            String body = new ObjectMapper().writeValueAsString(dto);

            String result = mockMvc.perform(post(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
            assertNotNull(result);
        }

        @Test
        @Sql("classpath:/sql/clear_data.sql")
        @Sql("classpath:/sql/data.sql")
        void shouldReturn401() throws Exception{
            LoginRequest dto = new LoginRequest("max","ma");
            String body = new ObjectMapper().writeValueAsString(dto);

            mockMvc.perform(post(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body))
                .andExpect(status().isUnauthorized());
        }
    }

}