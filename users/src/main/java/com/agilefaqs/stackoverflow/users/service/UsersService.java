package com.agilefaqs.stackoverflow.users.service;

import com.agilefaqs.stackoverflow.exceptions.ApplicationException;
import com.agilefaqs.stackoverflow.users.dao.UsersDao;
import com.agilefaqs.stackoverflow.users.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class UsersService {

    private static Logger log = LoggerFactory.getLogger(UsersService.class);

    private UsersDao usersDao;

    @Autowired
    public UsersService(UsersDao usersDao) {
        this.usersDao = usersDao;
    }


    public String addNew(User input) {
        final User existingUser = usersDao.get(input.getUserId());
        if(existingUser !=null) {
            input.setUserId(generateAnotherId(input.getUserId()));
        }
        usersDao.save(input);
        return input.getUserId();
    }

    public User get(String userId) {
        final User user = usersDao.get(userId);
        if(user ==null)
            throw new ApplicationException("User not found", HttpStatus.NOT_FOUND);
        return user;
    }

    private String generateAnotherId(String userId) {
        return userId+usersDao.getNumberOfUsers()+1;
    }

}
