package com.agilefaqs.stackoverflow.gateway.clients;


import com.agilefaqs.stackoverflow.exceptions.GenericHystrixCommand;
import com.agilefaqs.stackoverflow.gateway.model.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Component
public class SessionsClient {

    private SessionsFeignClient sessionsFeignClient;
    private Map<String, UserDetail> sessionsTokens = new ConcurrentHashMap<>();

    @Autowired
    public SessionsClient(SessionsFeignClient sessionsFeignClient) {
        this.sessionsFeignClient = sessionsFeignClient;
    }

    public UserDetail validateToken(AuthRequest authRequest) {
        return GenericHystrixCommand.execute(
                "gateway.auth",
                 "sessions.validate",
            () -> {
                final UserDetail isValid = sessionsFeignClient.validateToken(authRequest);
                sessionsTokens.put(authRequest.getToken(), isValid);
                return isValid;
            },
            e -> sessionsTokens.get(authRequest.getToken())
        );
    }
}
