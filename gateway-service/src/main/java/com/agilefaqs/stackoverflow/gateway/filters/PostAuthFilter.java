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
        if (context.getRequest().getRequestURI().equals("/api/sessions/login")
        && context.getResponse().getStatus() == 200
        ){

            logger.info("Zuul post auth filter for successful auth");
            try (final InputStream responseDataStream = context.getResponseDataStream()) {
                String responseData = CharStreams.toString(new InputStreamReader(responseDataStream, "UTF-8"));
                logger.info("Zuul post auth filter BODY: {}", responseData);
                JsonObject jsonObject = new JsonParser().parse(responseData).getAsJsonObject();
                logger.info("Zuul post auth filter response JSON{} ",jsonObject);

                Cookie authCookie = new Cookie("user-auth-token", jsonObject.get("token").getAsString());
                authCookie.setDomain("localhost");
                authCookie.setPath("/");
                //authCookie.setHttpOnly(true);

              /*  Cookie userDetail = new Cookie("user-detail", jsonObject.get("userDetail").toString());
                logger.info(" User Detail is {}",jsonObject.get("userDetail").toString());
                userDetail.setDomain("localhost");
                userDetail.setPath("/");
                userDetail.setHttpOnly(true);*/

                logger.info("Zuul post auth filter : Setting cookie {}",authCookie.getValue());
                context.getResponse().addCookie(authCookie);
                //context.getResponse().addCookie(userDetail);
                context.setResponseBody(responseData);
            }
            catch (Exception e) {
                logger.error("Something blew up while setting cookies "+ e.getMessage(),e);
                //throw new ZuulException(e, INTERNAL_SERVER_ERROR.value(), e.getMessage());
                throw new RuntimeException(e);
            }

        }


        return null;


    }
}
