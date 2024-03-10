package org.aston.ems.admin_service.controller;

import org.aston.ems.admin_service.ApplicationTest;
import org.aston.ems.admin_service.dto.UserResDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.aston.ems.admin_service.TestFactory.USER_REQ_DTO;
import static org.aston.ems.admin_service.TestFactory.USER_REQ_NEW_DTO;
import static org.aston.ems.admin_service.TestFactory.USER_RES_DTO;
import static org.aston.ems.admin_service.util.JsonUtils.asJsonString;
import static org.aston.ems.admin_service.util.JsonUtils.jsonToList;
import static org.aston.ems.admin_service.util.JsonUtils.jsonToObject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
//@ContextConfiguration(classes = SecurityConfig.class)
@WebAppConfiguration
@Disabled
class UserControllerTest extends ApplicationTest {

    private final String url = "/api/v1/admin/users";
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();
    }

    @Nested
    class GetAllTest {

        @Test
        @Sql("classpath:/sql/clear_data.sql")
        @Sql("classpath:/sql/data.sql")
        @Disabled
        void shouldReturn200() throws Exception {

            String result = mvc.perform(get(url)
                    .contentType(MediaType.APPLICATION_JSON).characterEncoding(StandardCharsets.UTF_8)
                    .with(httpBasic("max", "max"))
                )
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

            List<UserResDto> dtos = jsonToList(result, UserResDto.class);
            assertEquals(4, dtos.size());
        }

        @Test
        @Sql("classpath:/sql/clear_data.sql")
        @Sql("classpath:/sql/data.sql")
        @Disabled
        void shouldReturn401() throws Exception {

            String result = mvc.perform(get(url)
                    .contentType(MediaType.APPLICATION_JSON).characterEncoding(StandardCharsets.UTF_8)
                    .with(httpBasic("max", "ma"))
                )
                .andExpect(status().isUnauthorized())
                .andReturn().getResponse().getContentAsString();

        }


    }

    @Nested
    class getByNameTest {

        @Test
        @Sql("classpath:/sql/clear_data.sql")
        @Sql("classpath:/sql/data.sql")
        @Disabled
        void shouldReturn200() throws Exception {

            String result = mvc.perform(get(url+"/max")
                    .contentType(MediaType.APPLICATION_JSON).characterEncoding(StandardCharsets.UTF_8)
                    .with(httpBasic("max", "max"))
                )
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

            UserResDto dto = jsonToObject(result, UserResDto.class);
            assertEquals(USER_RES_DTO, dto);
        }

    }


    @Nested
    class updateTest {

        @WithMockUser(authorities = "ADMIN")
        @Test
        @Sql("classpath:/sql/clear_data.sql")
        @Sql("classpath:/sql/data.sql")
        @Disabled
        void shouldReturn200() throws Exception {
            mvc.perform(put(url)
                    .contentType(MediaType.APPLICATION_JSON).characterEncoding(StandardCharsets.UTF_8)
                    .content(asJsonString(USER_RES_DTO))
                )
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        }

        @WithMockUser
        @Test
        @Sql("classpath:/sql/clear_data.sql")
        @Sql("classpath:/sql/data.sql")
        @Disabled
        void shouldReturn403() throws Exception {
            mvc.perform(put(url)
                    .contentType(MediaType.APPLICATION_JSON).characterEncoding(StandardCharsets.UTF_8)
                    .content(asJsonString(USER_REQ_DTO))
                )
                .andExpect(status().isForbidden())
                .andReturn().getResponse().getContentAsString();
        }

    }

    @Nested
    class createTest {

        @WithMockUser(authorities = "ADMIN")
        @Test
        @Disabled
        @Sql("classpath:/sql/clear_data.sql")
        @Sql("classpath:/sql/data.sql")
        void shouldReturn201() throws Exception {
            mvc.perform(post(url)
                    .contentType(MediaType.APPLICATION_JSON).characterEncoding(StandardCharsets.UTF_8)
                    .content(asJsonString(USER_REQ_NEW_DTO))
                )
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        }

        @WithMockUser
        @Test
        @Sql("classpath:/sql/clear_data.sql")
        @Sql("classpath:/sql/data.sql")
        @Disabled
        void shouldReturn403() throws Exception {
            mvc.perform(post(url)
                    .contentType(MediaType.APPLICATION_JSON).characterEncoding(StandardCharsets.UTF_8)
                    .content(asJsonString(USER_REQ_DTO))
                )
                .andExpect(status().isForbidden())
                .andReturn().getResponse().getContentAsString();
        }
    }

    @Nested
    class deleteTest {

        @WithMockUser(authorities = "ADMIN")
        @Test
        @Sql("classpath:/sql/clear_data.sql")
        @Sql("classpath:/sql/data.sql")
        @Disabled
        void shouldReturn200() throws Exception {

           mvc.perform(delete(url + "/max")
                    .contentType(MediaType.APPLICATION_JSON).characterEncoding(StandardCharsets.UTF_8)
                )
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        }

        @WithMockUser
        @Test
        @Sql("classpath:/sql/clear_data.sql")
        @Sql("classpath:/sql/data.sql")
        @Disabled
        void shouldReturn403() throws Exception {

            String result = mvc.perform(delete(url + "/max")
                    .contentType(MediaType.APPLICATION_JSON).characterEncoding(StandardCharsets.UTF_8)
                )
                .andExpect(status().isForbidden())
                .andReturn().getResponse().getContentAsString();

        }
    }
}