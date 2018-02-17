package com.agilefaqs.stackoverflow.gateway.model;

public class AuthRequest {


    public String getToken() {
        return token;
    }

    private final String token;

    public AuthRequest(String token) {
        this.token = token;
    }

}
