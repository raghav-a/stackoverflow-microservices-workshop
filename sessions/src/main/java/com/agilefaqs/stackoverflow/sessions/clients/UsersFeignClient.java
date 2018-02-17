package com.agilefaqs.stackoverflow.sessions.clients;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient("users-service")
public interface UsersFeignClient {

        @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}")
        UserDetail getUserDetails(@PathVariable("userId") String userId);



}
