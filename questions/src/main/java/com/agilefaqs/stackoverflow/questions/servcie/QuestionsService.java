package com.agilefaqs.stackoverflow.questions.servcie;

import com.agilefaqs.stackoverflow.questions.dao.QuestionsDao;
import com.agilefaqs.stackoverflow.questions.model.Question;
import com.agilefaqs.stackoverflow.questions.queues.Queues;
import com.agilefaqs.stackoverflow.questions.queues.Queues.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class QuestionsService {

    private QuestionsDao questionsDao;
    private Channel<Question> questionsChannel;


    @Autowired
    public QuestionsService(QuestionsDao questionsDao, Queues queues) {
        questionsChannel = queues.createChannelForTopic("Posted_Questions");
        this.questionsDao = questionsDao;
    }


    public Question get(String questionId) {
        return questionsDao.get(questionId);
    }

    public String save(Question question) {
        questionsDao.save(question);
        questionsChannel.publish(question);
        return question.getId();
    }

    public void update(String questionId, Question input) {
        input.setId(questionId);
        questionsDao.save(input);
        questionsChannel.publish(input);
    }

    public void upvote(String questionId, String userId) {
        questionsDao.get(questionId).upvote(userId);
    }

    public void downvote(String questionId, String userId) {
        questionsDao.get(questionId).downvote(userId);
    }

    public Collection<Question> listAll() {
        return questionsDao.getAll();
    }
}
