package org.aston.ems.user_service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aston.ems.user_service.service.RatingStudentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource("classpath:application.yml")
public class Config {

    @Value("${userService.value.URI}")
    private String URI; //Заменить на endpoint когда он будет написан в application.yml

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    public RatingStudentService ratingStudentService() {
        return new RatingStudentService(URI);
    }

    @Bean
    public ObjectMapper objectMapper() {return new ObjectMapper();}
}
