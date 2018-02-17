package com.agilefaqs.stackoverflow.gateway.filters;

import com.agilefaqs.stackoverflow.exceptions.ApplicationException;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;


public class ErrorFilter extends ZuulFilter{

    private static Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 100;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        final Throwable cause = ctx.getThrowable().getCause();
        log.warn("Cause for Error Filter : ",cause);
        if(cause instanceof ApplicationException){
            final Throwable throwable = ((Throwable) ctx.remove("throwable")).getCause();
            log.warn("The Exception trace in error filter.",throwable);
            ctx.getResponse().setStatus(HttpStatus.FORBIDDEN.value());
            ctx.setResponseBody("Token Authentication Failed.");
        }
        return null;
    }
}
