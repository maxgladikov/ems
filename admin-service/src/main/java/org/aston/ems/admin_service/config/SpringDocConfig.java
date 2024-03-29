package org.aston.ems.admin_service.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class SpringDocConfig {

    @Bean
    OpenAPI baseOpenApi(){
        return new OpenAPI().info(new Info().title("Admin Service Doc").version("1.0.0").description("Spring Doc"));
    }
}
