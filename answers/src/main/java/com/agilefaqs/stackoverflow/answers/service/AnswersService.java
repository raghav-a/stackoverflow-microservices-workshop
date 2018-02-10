package com.agilefaqs.stackoverflow.answers.service;

import com.agilefaqs.stackoverflow.answers.model.Answer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class AnswersService {

    private Map<String, Answer> answers = new HashMap<>();

    public Answer get(String questionId) {
        return answers.get(questionId);
    }

    public String save(Answer answer) {
        final String id = answers.keySet().size() + 1 + "";
        answer.setId(id);
        answers.put(id, answer);
        return id;
    }

    public void update(String questionId, Answer input) {
        final Answer answer = answers.get(questionId);
        if(input.getAnswer()!=null)
            answer.setAnswer(input.getAnswer());
        if(input.getTags()!=null)
            answer.setTags(input.getTags());

    }

    public void upvote(String questionId) {
        answers.get(questionId).upvote();
    }

    public void downvote(String questionId) {
        answers.get(questionId).downvote();
    }

    public List<Answer> getAllFor(String questionId) {
        return answers.values()
            .stream()
            .filter(answer -> answer.getQuestionId().equals(questionId))
            .collect(Collectors.toList());
    }
}
