package com.agilefaqs.stackoverflow.questions.controllers;

import com.agilefaqs.stackoverflow.questions.model.Question;
import com.agilefaqs.stackoverflow.questions.servcie.QuestionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("questions")
public class QuestionsController {

    private static Logger log = LoggerFactory.getLogger(QuestionsController.class);

    @Autowired
    private QuestionsService questionsService;

    public @ResponseBody  Question get(@PathVariable("questionId") String questionId) {
        return questionsService.get(questionId);
    }

    @RequestMapping(value = "/{questionId}", method = PUT)
    public ResponseEntity<?>  update(@PathVariable("questionId") String questionId, @RequestHeader("X-USER-ID") String userId, @RequestBody Question input) {
        input.setPostedBy(userId);
        questionsService.update(questionId, input);
        return ResponseEntity
            .noContent()
            .build();

    }

    @RequestMapping(method = POST)
    ResponseEntity<?> add(@RequestBody @Valid Question input, @RequestHeader("X-USER-ID") String userId) {
        log.info("Save Question Called by {} : {}",userId, input);
        input.setPostedBy(userId);
        String id = questionsService.save(input);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body("{\"questionId\":\""+id+"\"}");


    }

    @RequestMapping(value = "/{questionId}/upvote", method = POST)
    public ResponseEntity<?>  upvote(@PathVariable("questionId") String questionId, @RequestHeader("X-USER-ID") String userId) {
        questionsService.upvote(questionId, userId);
        return ResponseEntity
            .noContent()
            .build();

    }

    @RequestMapping(value = "/{questionId}/downvote", method = POST)
    public ResponseEntity<?>  downvote(@PathVariable("questionId") String questionId, @RequestHeader("X-USER-ID") String userId) {
        questionsService.downvote(questionId, userId);
        return ResponseEntity
            .noContent()
            .build();

    }

}
