package com.agilefaqs.stackoverflow.answers.controllers;

import com.agilefaqs.stackoverflow.answers.model.Answer;
import com.agilefaqs.stackoverflow.answers.service.AnswersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("answers")
public class AnswersController {


    @Autowired
    AnswersService answersService;

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@RequestBody Answer input) {
        input.validate();
        String id = answersService.save(input);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body("{\"answerId\":\""+id+"\"}");


    }

    @RequestMapping(value = "/{answerId}", method = RequestMethod.GET)
    public @ResponseBody
    Answer get(@PathVariable("answerId") String answerId) {
        return answersService.get(answerId);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public @ResponseBody
    List<Answer> getAll(@RequestHeader("questionId") String questionId) {
        return answersService.getAllFor(questionId);
    }

    @RequestMapping(value = "/{answerId}", method = RequestMethod.PUT)
    public ResponseEntity<?>  update(@PathVariable("answerId") String answerId, @RequestBody Answer input) {
        answersService.update(answerId, input);
        return ResponseEntity
            .noContent()
            .build();

    }

    @RequestMapping(value = "/{answerId}/upvote", method = RequestMethod.POST)
    public ResponseEntity<?>  upvote(@PathVariable("answerId") String answerId) {
        answersService.upvote(answerId);
        return ResponseEntity
            .noContent()
            .build();

    }

    @RequestMapping(value = "/{answerId}/downvote", method = RequestMethod.POST)
    public ResponseEntity<?>  downvote(@PathVariable("answerId") String answerId) {
        answersService.upvote(answerId);
        return ResponseEntity
            .noContent()
            .build();

    }

}
