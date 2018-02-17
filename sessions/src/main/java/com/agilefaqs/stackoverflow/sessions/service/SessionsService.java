package com.agilefaqs.stackoverflow.sessions.service;

import com.agilefaqs.stackoverflow.exceptions.ApplicationException;
import com.agilefaqs.stackoverflow.sessions.clients.UserDetail;
import com.agilefaqs.stackoverflow.sessions.clients.UsersClient;
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
    private UsersClient usersClient;

    //private Map<String, String> userIdPasswordMap = new HashMap<>();
    private Map<String, UserDetail> userIdTokenMap = new HashMap<>();


    @Autowired
    public SessionsService(SessionsDao sessionsDao, UsersClient usersClient) {
        this.sessionsDao = sessionsDao;
        this.usersClient = usersClient;
        //userIdPasswordMap.put("raghav", "qwedsa");
        //userIdPasswordMap.put("hari", "qwedsa");
        //userIdPasswordMap.put("naresh", "qwedsa");
    }


    public String login(String userId, String password) {
        final UserDetail userDetails = usersClient.getUserDetails(userId);
        if(userDetails==null)
        {
            throw new ApplicationException("User Id Incorrect", HttpStatus.NOT_FOUND);
        }
        if(!userDetails.getPassword().equals(password))
        {
            throw new ApplicationException("Password Incorrect", HttpStatus.BAD_REQUEST);
        }
        return generateToken(userId, userDetails);
    }

    private String generateToken(String userId, UserDetail userDetails) {
        final String token = UUID.randomUUID().toString();
        userIdTokenMap.put(token, userDetails);
        return token;
    }

    public UserDetail validateToken(String token) {
        final UserDetail userDetail = userIdTokenMap.get(token);
        if(userDetail==null)
        {
            throw new ApplicationException("Token incorrect/Not-found", HttpStatus.NOT_FOUND);
        }

        log.info(String.format("Token for %s is %s", userDetail.getUserId(), userDetail));
        return userDetail;
    }
}
