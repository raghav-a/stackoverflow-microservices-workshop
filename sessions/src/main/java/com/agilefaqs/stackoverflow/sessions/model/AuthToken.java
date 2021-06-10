package com.agilefaqs.stackoverflow.sessions.model;

public class AuthToken {
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;

    public AuthToken(String token) {
        this.token = token;
    }
}
