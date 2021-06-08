package com.agilefaqs.stackoverflow.gateway;

import com.agilefaqs.stackoverflow.gateway.clients.SessionsClient;
import com.agilefaqs.stackoverflow.gateway.config.AuthConfig;
import com.agilefaqs.stackoverflow.gateway.filters.AuthenticationFilter;
import com.agilefaqs.stackoverflow.gateway.filters.ErrorFilter;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.HTTPS_SCHEME;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.HTTP_SCHEME;


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

    @Bean
    public RegistryBuilder registryBuilder()  {
        return RegistryBuilder.<ConnectionSocketFactory> create()
            .register(HTTP_SCHEME, PlainConnectionSocketFactory.INSTANCE)
            .register(HTTPS_SCHEME, SSLConnectionSocketFactory.getSocketFactory());
    }

    /*@Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }*/

    @Bean
    public FilterRegistrationBean corsFilter2() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }



}
