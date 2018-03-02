package com.agilefaqs.stackoverflow.gateway.filters;

import com.agilefaqs.stackoverflow.exceptions.ApplicationException;
import com.agilefaqs.stackoverflow.gateway.clients.SessionsFeignClient;
import com.agilefaqs.stackoverflow.gateway.clients.UserDetail;
import com.agilefaqs.stackoverflow.gateway.config.AuthConfig;
import com.agilefaqs.stackoverflow.gateway.model.AuthRequest;
import com.google.common.base.Preconditions;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;


public class AuthenticationFilter extends ZuulFilter {

    private SessionsFeignClient sessionsClient;

    private AuthConfig authConfig;

    private static Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

    public AuthenticationFilter(SessionsFeignClient sessionsClient, AuthConfig authConfig) {
        this.sessionsClient = sessionsClient;
        this.authConfig = authConfig;
        log.info("service api auth configuration :" + authConfig);
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
        log.info("Authentication filter invoked");

        RequestContext ctx = RequestContext.getCurrentContext();
        try {
            HttpServletRequest request = ctx.getRequest();
            log.info("request uri : " + request.getRequestURI());
            final String token = request.getHeader("token");
            final AuthRequest authRequest = new AuthRequest(token);
            if (authConfig.needsAuthentication(request.getRequestURI(), request.getMethod())) {
                UserDetail userDetail = sessionsClient.validateToken(authRequest);
                log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
                log.info(String.format("Is response valid : %s", userDetail!=null));
                Preconditions.checkNotNull(userDetail);
                ctx.addZuulRequestHeader("X-USER-ID", userDetail.getUserId());
            }

        } catch (RuntimeException e) {
            e.printStackTrace();
            log.info("Error message " + e.getMessage());
            ctx.getResponse().setStatus(HttpStatus.FORBIDDEN.value());
            ctx.setResponseBody("Token Authentication Failed.");
            throw new ApplicationException("Token Authentication Failed",HttpStatus.FORBIDDEN);
        }

        return null;
    }
}
