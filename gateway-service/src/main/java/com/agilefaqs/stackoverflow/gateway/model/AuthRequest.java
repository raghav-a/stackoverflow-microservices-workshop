package com.agilefaqs.stackoverflow.gateway.model;

public class AuthRequest {
    private final String userId;

    public String getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }

    private final String token;

    public AuthRequest(String userId, String token) {
        this.userId = userId;
        this.token = token;
    }
}
