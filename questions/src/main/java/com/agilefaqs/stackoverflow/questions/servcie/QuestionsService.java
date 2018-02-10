package com.agilefaqs.stackoverflow.questions.servcie;

import com.agilefaqs.stackoverflow.questions.model.Question;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class QuestionsService {

    private Map<String, Question> questions = new HashMap<>();

    public Question get(String questionId) {
        return questions.get(questionId);
    }

    public String save(Question question) {
        final String id = questions.keySet().size() + 1 + "";
        question.setQuestionId(id);
        questions.put(id, question);
        return id;
    }

    public void update(String questionId, Question input) {
        final Question question = questions.get(questionId);
        if(input.getQuestion()!=null)
            question.setQuestion(input.getQuestion());
        if(input.getTags()!=null)
            question.setTags(input.getTags());

    }

    public void upvote(String questionId) {
        questions.get(questionId).upvote();
    }

    public void downvote(String questionId) {
        questions.get(questionId).downvote();
    }
}
