package org.aston.ems.user_service.config;

import org.aston.ems.user_service.service.RatingStudentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.yml")
public class Config {

    @Value("${value.URI}")
    private String URI; //Заменить на endpoint когда он будет написан в application.yml

    @Bean
    public RatingStudentService ratingStudentService() {
        return new RatingStudentService(URI);
    }


}
