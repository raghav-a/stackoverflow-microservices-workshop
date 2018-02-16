package com.agilefaqs.stackoverflow.sessions.servcie;

import com.agilefaqs.stackoverflow.exceptions.ApplicationException;
import com.agilefaqs.stackoverflow.sessions.controllers.SessionsController;
import com.agilefaqs.stackoverflow.sessions.dao.SessionsDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class SessionsService {

    private static Logger log = LoggerFactory.getLogger(SessionsService.class);

    private SessionsDao sessionsDao;

    private Map<String, String> userIdPasswordMap = new HashMap<>();
    private Map<String, String> userIdTokenMap = new HashMap<>();


    @Autowired
    public SessionsService(SessionsDao sessionsDao) {
        this.sessionsDao = sessionsDao;
        userIdPasswordMap.put("raghav", "qwedsa");
        userIdPasswordMap.put("hari", "qwedsa");
        userIdPasswordMap.put("naresh", "qwedsa");
    }


    public String login(String userId, String password) {
        final String savedPassword = userIdPasswordMap.get(userId);
        if(savedPassword==null)
        {
            throw new ApplicationException("User Id Incorrect", HttpStatus.NOT_FOUND);
        }
        if(!savedPassword.equals(password))
        {
            throw new ApplicationException("Password Incorrect", HttpStatus.BAD_REQUEST);
        }
        return generateToken(userId);
    }

    private String generateToken(String userId) {
        final String token = UUID.randomUUID().toString();
        userIdTokenMap.put(userId, token);
        return token;
    }

    public Boolean validateToken(String userId, String token) {
        final String savedToken = userIdTokenMap.get(userId);
        log.info(String.format("Token for %s is %s", userId, savedToken));
        if(savedToken==null)
        {
            throw new ApplicationException("Token not found", HttpStatus.NOT_FOUND);
        }
        if(!savedToken.equals(token))
        {
            throw new ApplicationException("Token Incorrect", HttpStatus.BAD_REQUEST);
        }

        return true;
    }
}
