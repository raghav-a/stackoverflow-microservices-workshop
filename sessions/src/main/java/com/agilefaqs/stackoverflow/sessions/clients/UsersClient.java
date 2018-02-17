package com.agilefaqs.stackoverflow.sessions.clients;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UsersClient {

    private UsersFeignClient usersFeignClient;

    @Autowired
    public UsersClient(UsersFeignClient usersFeignClient) {
        this.usersFeignClient = usersFeignClient;
    }

    public UserDetail getUserDetails(String userId) {
        return usersFeignClient.getUserDetails(userId);
    }
}
