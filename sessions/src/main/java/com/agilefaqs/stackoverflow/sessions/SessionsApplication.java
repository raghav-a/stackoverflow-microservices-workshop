package com.agilefaqs.stackoverflow.sessions;

import com.agilefaqs.stackoverflow.sessions.dao.HashMapQuestionsDao;
import com.agilefaqs.stackoverflow.sessions.dao.SessionsDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication

@EnableEurekaClient
public class SessionsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SessionsApplication.class);
    }


    @Configuration
    public static class Config{

        @Bean
        public SessionsDao dao(){
            return new HashMapQuestionsDao();
        }
    }
}
