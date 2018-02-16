package com.agilefaqs.stackoverflow.gateway;

import com.agilefaqs.stackoverflow.gateway.clients.SessionsClient;
import com.agilefaqs.stackoverflow.gateway.config.AuthConfig;
import com.agilefaqs.stackoverflow.gateway.model.AuthRequest;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class AuthenticationFilter extends ZuulFilter {

    @Autowired
    SessionsClient sessionsClient;

    @Autowired
    AuthConfig authConfig;

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
        log.info("auth filter invoked");
        log.info("service api config :"+authConfig);
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        final String token = request.getHeader("token");
        final String userId = request.getHeader("userId");
        log.info("request uri : "+request.getRequestURI() );
        if(request.getRequestURI().contains("login"))
            return null;
        try {
            if(authConfig.needsAuthentication(request.getRequestURI(), request.getMethod())){
                Boolean response = sessionsClient.validateToken(new AuthRequest(userId, token));
                log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
                log.info(String.format("Is response valid : %s", response));
            }


        }catch (RuntimeException e){
            e.printStackTrace();
            log.info("Error message "+e.getMessage());
            throw e;
        }

        return null;
    }
}
