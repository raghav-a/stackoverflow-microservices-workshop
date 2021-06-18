package com.agilefaqs.stackoverflow.sessions.model;

import com.agilefaqs.stackoverflow.sessions.clients.UserDetail;

public class AuthResponse {
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    private UserDetail userDetail;

    public AuthResponse(String token, UserDetail userDetail) {
        this.token = token;
        this.userDetail = userDetail;
    }
}
