package com.agilefaqs.stackoverflow.gateway.clients;

import com.agilefaqs.stackoverflow.gateway.model.AuthRequest;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;



@FeignClient("sessions-service")
public interface SessionsFeignClient {

        @RequestMapping(method = RequestMethod.POST, value = "/sessions/validateToken")
        Boolean validateToken(@RequestBody AuthRequest token);



}
