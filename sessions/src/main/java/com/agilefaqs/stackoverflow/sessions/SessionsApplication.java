package com.agilefaqs.stackoverflow.sessions;

import com.agilefaqs.stackoverflow.exceptions.ControllerExceptionAdvice;
import com.agilefaqs.stackoverflow.sessions.dao.HashMapQuestionsDao;
import com.agilefaqs.stackoverflow.sessions.dao.SessionsDao;
import org.apache.catalina.filters.RequestDumperFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;


@SpringBootApplication
@EnableFeignClients
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

        @Bean
        public ControllerExceptionAdvice controllerAdvice(){
            return new ControllerExceptionAdvice();


        }
        @Bean
        public FilterRegistrationBean requestDumperFilter() {
            FilterRegistrationBean registration = new FilterRegistrationBean();
            Filter requestDumperFilter = new RequestDumperFilter();
            registration.setFilter(requestDumperFilter);
            registration.addUrlPatterns("/*");
            return registration;
        }
    }
}
