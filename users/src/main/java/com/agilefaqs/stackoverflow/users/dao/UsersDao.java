package com.agilefaqs.stackoverflow.users.dao;


import com.agilefaqs.stackoverflow.users.model.User;

public interface UsersDao {

    void save(User input);

    User get(String userId);

    int getNumberOfUsers();
}
