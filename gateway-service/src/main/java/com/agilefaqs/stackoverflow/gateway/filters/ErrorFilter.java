package com.agilefaqs.stackoverflow.gateway.filters;

import com.agilefaqs.stackoverflow.exceptions.ApplicationException;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.http.HttpStatus;


public class ErrorFilter extends ZuulFilter{
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
        System.out.println("Error filter called ji ");
        RequestContext ctx = RequestContext.getCurrentContext();
        final Throwable throwable = ((Throwable) ctx.remove("throwable")).getCause();
        if(throwable instanceof ApplicationException){
            System.out.println("Exp is "+throwable.getMessage());
            ctx.getResponse().setStatus(HttpStatus.FORBIDDEN.value());
            ctx.setResponseBody("Token Authentication Failed.");
//            ctx.setThrowable(null);

        }
        return null;
    }
}
