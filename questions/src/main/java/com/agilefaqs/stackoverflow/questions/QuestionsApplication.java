package com.agilefaqs.stackoverflow.questions;

import com.agilefaqs.stackoverflow.questions.controllers.ControllerExceptionAdvice;
import com.agilefaqs.stackoverflow.questions.dao.HashMapQuestionsDao;
import com.agilefaqs.stackoverflow.questions.dao.QuestionsDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class QuestionsApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuestionsApplication.class);
    }


    @Configuration
    public static class Config{

        @Bean
        public QuestionsDao dao(){
            return new HashMapQuestionsDao();
        }

        @Bean
        public ControllerExceptionAdvice controllerAdvice(){
            return new ControllerExceptionAdvice();
        }
    }
}
