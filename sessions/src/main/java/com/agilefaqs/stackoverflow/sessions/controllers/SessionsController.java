package com.agilefaqs.stackoverflow.sessions.controllers;

import com.agilefaqs.stackoverflow.sessions.model.AuthRequest;
import com.agilefaqs.stackoverflow.sessions.model.LoginRequest;
import com.agilefaqs.stackoverflow.sessions.service.SessionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sessions")
public class SessionsController {

    private static Logger log = LoggerFactory.getLogger(SessionsController.class);

    @Autowired
    SessionsService sessionsService;

    @RequestMapping(value = "/validateToken", method = RequestMethod.POST)
    public Boolean validateToken(@RequestBody AuthRequest authRequest) {
        log.info("AuthRequest received : "+authRequest.toString());
        final Boolean response = sessionsService.validateToken(authRequest.getUserId(), authRequest.getToken());
        log.info("Validate token response is : "+response);
        return response;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody LoginRequest loginRequest) {
        log.info("LoginRequest received : "+loginRequest);
        return sessionsService.login(loginRequest.getUserId(), loginRequest.getPassword());
    }


}
