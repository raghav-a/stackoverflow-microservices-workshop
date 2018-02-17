package com.agilefaqs.stackoverflow.sessions.clients;

public class UserDetail {

    public UserDetail() {
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    private String userId;
    private String password;
    private String email;
    private String firstName;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private String lastName;

    public UserDetail(String userId, String password, String email, String firstName, String lastName) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }



}
