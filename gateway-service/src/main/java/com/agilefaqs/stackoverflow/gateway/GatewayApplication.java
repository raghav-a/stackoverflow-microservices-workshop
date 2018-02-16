package com.agilefaqs.stackoverflow.gateway;

import com.agilefaqs.stackoverflow.gateway.clients.SessionsClient;
import com.agilefaqs.stackoverflow.gateway.model.AuthRequest;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@SpringBootApplication
@EnableZuulProxy
@RestController
@EnableFeignClients
@EnableDiscoveryClient
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
    public AuthenticationFilter filter(SessionsClient sessionsClient) {
        System.out.println("Filter created");
        return new AuthenticationFilter(sessionsClient);
    }


    public static class AuthenticationFilter extends ZuulFilter {

        @Autowired
        SessionsClient sessionsClient;

        private static Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

        public AuthenticationFilter(SessionsClient sessionsClient) {
            this.sessionsClient = sessionsClient;
        }

        @Override
        public String filterType() {
            return "pre";
        }
        @Override
        public int filterOrder() {
            return 1;
        }
        @Override
        public boolean shouldFilter() {
            return true;
        }

        @Override
        public Object run() {
            System.out.println("filter invoked");
            RequestContext ctx = RequestContext.getCurrentContext();
            HttpServletRequest request = ctx.getRequest();
            final String token = request.getHeader("token");
            final String userId = request.getHeader("userId");
            log.info("request uri : "+request.getRequestURI() );
            if(request.getRequestURI().contains("login"))
                return null;
            try {
                Boolean response = sessionsClient.validateToken(new AuthRequest(userId, token));
                log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
                log.info(String.format("Is response valid : %s", response));

            }catch (RuntimeException e){
                e.printStackTrace();
                log.info("Error message "+e.getMessage());
            }

            return null;
        }
    }

}
