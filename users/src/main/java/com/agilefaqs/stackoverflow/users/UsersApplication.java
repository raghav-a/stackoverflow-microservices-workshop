package com.agilefaqs.stackoverflow.users;

import com.agilefaqs.stackoverflow.users.dao.HashMapUsersDao;
import com.agilefaqs.stackoverflow.users.dao.UsersDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication

@EnableEurekaClient
public class UsersApplication {
    public static void main(String[] args) {
        SpringApplication.run(UsersApplication.class);
    }


    @Configuration
    public static class Config{

        @Bean
        public UsersDao dao(){
            return new HashMapUsersDao();
        }
    }
}
