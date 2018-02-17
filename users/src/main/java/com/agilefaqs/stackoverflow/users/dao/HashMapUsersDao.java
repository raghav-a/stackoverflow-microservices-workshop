package com.agilefaqs.stackoverflow.users.dao;

import com.agilefaqs.stackoverflow.exceptions.ApplicationException;
import com.agilefaqs.stackoverflow.users.model.User;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class HashMapUsersDao implements UsersDao {

    private Map<String, User> users = new HashMap<>();

    public HashMapUsersDao() {

        users.put("raghav", new User("raghav", "qwedsa", "raghav@agilefaqs.com", "raghav", "agarwal"));
        users.put("hari", new User("hari", "qwedsa", "hari@agilefaqs.com", "hari", "krishnan"));
        users.put("naresh", new User("naresh", "qwedsa", "naresh@agilefaqs.com", "naresh", "jain"));

    }

    @Override
    public void save(User input) {
        users.put(input.getUserId(),input);
    }

    @Override
    public User get(String userId) {
        return users.get(userId);
    }

    @Override
    public int getNumberOfUsers() {
        return users.size();
    }
}
