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
        log.info("Authentication filter invoked for : "+RequestContext.getCurrentContext().getRequest().getRequestURI());
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        try {
            final AuthRequest authRequest = new AuthRequest(getToken(request));
            if (authConfig.needsAuthentication(request)) {
                UserDetail userDetail = authenticate(request, authRequest);
                addHeader(ctx, userDetail);
            }

        } catch (RuntimeException e) {
            return handleException(ctx, e);
        }

        return null;
    }

    private String getToken(HttpServletRequest request) {
        return request.getHeader("token");
    }

    private Object handleException(RequestContext ctx, RuntimeException e) {
        log.info("Error message " + e.getMessage());
        ctx.getResponse().setStatus(HttpStatus.FORBIDDEN.value());
        ctx.setResponseBody("Token Authentication Failed.");
        throw new ApplicationException("Token Authentication Failed",HttpStatus.FORBIDDEN);
    }

    private void addHeader(RequestContext ctx, UserDetail userDetail) {
        ctx.addZuulRequestHeader("X-USER-ID", userDetail.getUserId());
    }

    private UserDetail authenticate(HttpServletRequest request, AuthRequest authRequest) {
        UserDetail userDetail = sessionsClient.validateToken(authRequest);
        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
        log.info(String.format("Is response valid : %s", userDetail!=null));
        Preconditions.checkNotNull(userDetail);
        return userDetail;
    }
}
