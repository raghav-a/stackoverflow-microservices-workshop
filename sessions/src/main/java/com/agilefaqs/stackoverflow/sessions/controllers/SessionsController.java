package com.agilefaqs.stackoverflow.sessions.controllers;

import com.agilefaqs.stackoverflow.sessions.clients.UserDetail;
import com.agilefaqs.stackoverflow.sessions.model.AuthRequest;
import com.agilefaqs.stackoverflow.sessions.model.AuthToken;
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
    public UserDetail validateToken(@RequestBody AuthRequest authRequest) {
        log.info("AuthRequest received : "+authRequest.toString());
        final UserDetail response = sessionsService.validateToken(authRequest.getToken());
        log.info("Validate token response is : "+response);
        return response;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public AuthToken login(@RequestBody LoginRequest loginRequest) {
        System.out.println("login");
        log.info("LoginRequest received : "+loginRequest);
        String token = sessionsService.login(loginRequest.getUserId(), loginRequest.getPassword());
        return new AuthToken(token);
    }


}
