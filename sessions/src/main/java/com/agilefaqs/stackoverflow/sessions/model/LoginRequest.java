package com.agilefaqs.stackoverflow.sessions.model;

public class LoginRequest {
    private String userId;
    private String password;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
            "userId='" + userId + '\'' +
            ", password='" + password + '\'' +
            '}';
    }
}
