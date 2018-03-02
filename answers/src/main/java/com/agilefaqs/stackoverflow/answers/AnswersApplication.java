package com.agilefaqs.stackoverflow.answers;

import com.agilefaqs.stackoverflow.answers.dao.AnswersDao;
import com.agilefaqs.stackoverflow.answers.dao.HashMapAnswersDao;
import com.agilefaqs.stackoverflow.exceptions.ControllerExceptionAdvice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class AnswersApplication {
    public static void main(String[] args) {
        SpringApplication.run(AnswersApplication.class);
    }


    @Configuration
    public static class Config{

        @Bean
        public AnswersDao dao(){
            return new HashMapAnswersDao();
        }

        @Bean
        public ControllerExceptionAdvice controllerAdvice(){
            return new ControllerExceptionAdvice();
        }
    }
}
