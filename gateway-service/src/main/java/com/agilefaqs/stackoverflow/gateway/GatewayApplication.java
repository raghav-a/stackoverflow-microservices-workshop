package com.agilefaqs.stackoverflow.gateway;

import com.agilefaqs.stackoverflow.gateway.clients.SessionsClient;
import com.agilefaqs.stackoverflow.gateway.config.AuthConfig;
import com.agilefaqs.stackoverflow.gateway.filters.AuthenticationFilter;
import com.agilefaqs.stackoverflow.gateway.filters.ErrorFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@EnableZuulProxy
@RestController
@EnableFeignClients
@EnableDiscoveryClient
@EnableCaching
public class GatewayApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(GatewayApplication.class).web(true).run(args);
    }

    @Bean
    public AlwaysSampler defaultSampler() {
        return new AlwaysSampler();
    }


    @Bean
    @Autowired
    public AuthenticationFilter filter(SessionsClient sessionsClient, AuthConfig authConfig) {
        return new AuthenticationFilter(sessionsClient, authConfig);
    }


    @Bean
    public ErrorFilter error() {
        return new ErrorFilter();
    }


}