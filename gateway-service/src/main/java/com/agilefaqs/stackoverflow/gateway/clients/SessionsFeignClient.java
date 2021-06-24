package com.agilefaqs.stackoverflow.gateway.clients;

import com.agilefaqs.stackoverflow.gateway.model.AuthRequest;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;


@FeignClient("sessions-service")
public interface SessionsFeignClient {

        @RequestMapping(method = RequestMethod.POST, value = "/sessions/validateToken")
        UserDetail validateToken(@RequestBody AuthRequest token);

        //@RequestMapping(method = RequestMethod.GET, value = "/sessions/authenticateToken")
        //UserDetail authenticateToken(@RequestParam("authToken") String authToken);



}
