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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthRequest)) return false;

        AuthRequest that = (AuthRequest) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        return token != null ? token.equals(that.token) : that.token == null;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (token != null ? token.hashCode() : 0);
        return result;
    }
}
