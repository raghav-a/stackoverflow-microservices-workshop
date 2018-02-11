package com.agilefaqs.stackoverflow.answers.service;

import com.agilefaqs.stackoverflow.answers.dao.AnswersDao;
import com.agilefaqs.stackoverflow.answers.dao.HashMapAnswersDao;
import com.agilefaqs.stackoverflow.answers.model.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class AnswersService {

    @Autowired
    private AnswersDao answersDao;

    public Answer get(String answerId) {
        return answersDao.get(answerId);
    }

    public String save(Answer answer) {
        answersDao.save(answer);
        return answer.getId();
    }

    public void update(String answerId, Answer input) {
        input.setId(answerId);
        answersDao.save(input);

    }

    public void upvote(String questionId) {
        answersDao.get(questionId).upvote();
    }

    public void downvote(String questionId) {
        answersDao.get(questionId).downvote();
    }

    public List<Answer> getAllFor(String questionId) {
        return answersDao.getAllAnswersFor(questionId);
    }
}
