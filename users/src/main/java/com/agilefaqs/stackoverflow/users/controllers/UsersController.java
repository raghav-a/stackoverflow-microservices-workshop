package com.agilefaqs.stackoverflow.users.controllers;

import com.agilefaqs.stackoverflow.users.model.User;
import com.agilefaqs.stackoverflow.users.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UsersController {

    private static Logger log = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UsersService usersService;

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@RequestBody User input) {
        log.info("user save request : "+input);
        String id = usersService.addNew(input);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body("{\"userId\":\""+id+"\"}");


    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public @ResponseBody
    User get(@PathVariable("userId") String userId) {
        log.info("User fetch request for : "+userId);
        return usersService.get(userId);
    }


}
