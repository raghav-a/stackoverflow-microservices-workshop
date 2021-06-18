package com.agilefaqs.stackoverflow.gateway.model;

public class AuthRequest {


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;

    public AuthRequest(String token) {
        this.token = token;
    }

}
