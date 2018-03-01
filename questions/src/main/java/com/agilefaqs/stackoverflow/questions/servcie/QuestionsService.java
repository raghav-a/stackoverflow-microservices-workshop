package com.agilefaqs.stackoverflow.questions.servcie;

import com.agilefaqs.stackoverflow.questions.dao.QuestionsDao;
import com.agilefaqs.stackoverflow.questions.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuestionsService {

    private QuestionsDao questionsDao;


    @Autowired
    public QuestionsService(QuestionsDao questionsDao) {
        this.questionsDao = questionsDao;
    }


    public Question get(String questionId) {
        return questionsDao.get(questionId);
    }

    public String save(Question question) {
        questionsDao.save(question);
        return question.getId();
    }

    public void update(String questionId, Question input) {
        input.setId(questionId);
        questionsDao.save(input);
    }

    public void upvote(String questionId) {
        questionsDao.get(questionId).upvote();
    }

    public void downvote(String questionId) {
        questionsDao.get(questionId).downvote();
    }
}
