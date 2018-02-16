package com.agilefaqs.stackoverflow.sessions.model;

public class AuthRequest {
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String userId;
    private String token;

    @Override
    public String toString() {
        return "AuthRequest{" +
            "userId='" + userId + '\'' +
            ", token='" + token + '\'' +
            '}';
    }
}
