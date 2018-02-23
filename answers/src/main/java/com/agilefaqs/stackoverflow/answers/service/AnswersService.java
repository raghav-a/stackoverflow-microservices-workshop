package com.agilefaqs.stackoverflow.answers.service;

import com.agilefaqs.stackoverflow.answers.dao.AnswersDao;
import com.agilefaqs.stackoverflow.answers.model.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnswersService {

    private AnswersDao answersDao;

    @Autowired
    public AnswersService(AnswersDao answersDao) {
        this.answersDao = answersDao;
    }

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

    public void upvote(String answerId, String userId) {
        answersDao.get(answerId).upvote(userId);
    }

    public void downvote(String questionId, String userId) {
        answersDao.get(questionId).downvote(userId);
    }

    public List<Answer> getAllFor(String questionId) {
        return answersDao.getAllAnswersFor(questionId);
    }
}
