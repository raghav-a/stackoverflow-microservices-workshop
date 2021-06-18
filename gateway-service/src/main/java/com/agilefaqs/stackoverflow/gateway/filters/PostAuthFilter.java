package com.agilefaqs.stackoverflow.gateway.filters;

import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


public class PostAuthFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Override
    public String filterType() {
        return POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        //logger.info("Zuul post auth filter context path: {}", context.getRequest().getContextPath());
        logger.info("Zuul post auth filter context path: {}", context.getRequest());
        logger.info("Zuul post auth filter context path: {}", context.getRequest().getRequestURI());
        //logger.info("Zuul post auth filter context path: {}", context.getRouteHost().getPath());
        //logger.info("Zuul post auth filter context path: {}", context.getRequest().getRequestURI());
        if (context.getRequest().getRequestURI().equals("/api/sessions/login")
        && context.getResponse().getStatus() == 200
        ){

            logger.info("Zuul post auth filter for successful auth");
            try (final InputStream responseDataStream = context.getResponseDataStream()) {
                if(responseDataStream == null) {
                    logger.info("Zuul post auth filter BODY: {}", "");
                    return null;
                }

                String responseData = CharStreams.toString(new InputStreamReader(responseDataStream, "UTF-8"));
                logger.info("1. Zuul post auth filter BODY: {}", responseData);
                JsonObject jsonObject = new JsonParser().parse(responseData).getAsJsonObject();
                logger.info("2. Zuul post auth filter for successful auth {} "+jsonObject);

                logger.info("4. Zuul post auth filter for successful auth token {} "+jsonObject.get("token").getAsString());
               // logger.info("3. Zuul post auth filter for successful auth {} "+jsonObject.getAsString());
                Cookie authCookie = new Cookie("user-auth-token", jsonObject.get("token").getAsString());
                authCookie.setDomain("localhost");
                authCookie.setPath("/");
                authCookie.setHttpOnly(true);
                context.getResponse().addCookie(authCookie);
                context.setResponseBody(responseData);
                logger.info("Zuul post auth filter for successful auth token {} "+jsonObject.get("token").getAsString());
            }
            catch (Exception e) {
                logger.error(e.getMessage(),e);
                //throw new ZuulException(e, INTERNAL_SERVER_ERROR.value(), e.getMessage());
            }

        }


        return null;


    }
}
