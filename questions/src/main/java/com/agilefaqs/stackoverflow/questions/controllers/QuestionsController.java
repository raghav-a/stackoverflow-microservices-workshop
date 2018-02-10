package com.agilefaqs.stackoverflow.questions.controllers;

import com.agilefaqs.stackoverflow.questions.model.Question;
import com.agilefaqs.stackoverflow.questions.servcie.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("questions")
public class QuestionsController {


    @Autowired
    QuestionsService questionsService;

    @RequestMapping(value = "/{questionId}", method = RequestMethod.GET)
    public @ResponseBody  Question get(@PathVariable("questionId") String questionId) {
        return questionsService.get(questionId);
    }

    @RequestMapping(value = "/{questionId}", method = RequestMethod.PUT)
    public ResponseEntity<?>  update(@PathVariable("questionId") String questionId, @RequestBody Question input) {
        questionsService.update(questionId, input);
        return ResponseEntity
            .noContent()
            .build();

    }

    @RequestMapping(value = "/{questionId}/upvote", method = RequestMethod.POST)
    public ResponseEntity<?>  upvote(@PathVariable("questionId") String questionId) {
        questionsService.upvote(questionId);
        return ResponseEntity
            .noContent()
            .build();

    }

    @RequestMapping(value = "/{questionId}/downvote", method = RequestMethod.POST)
    public ResponseEntity<?>  downvote(@PathVariable("questionId") String questionId) {
        questionsService.upvote(questionId);
        return ResponseEntity
            .noContent()
            .build();

    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@RequestBody Question input) {
        input.validate();
        String id = questionsService.save(input);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body("{\"questionId\":\""+id+"\"}");


    }

}
