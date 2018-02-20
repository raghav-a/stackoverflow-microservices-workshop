package com.agilefaqs.stackoverflow.answers.dao;

import com.agilefaqs.stackoverflow.answers.model.Answer;

import java.util.*;
import java.util.stream.Collectors;

import com.agilefaqs.stackoverflow.exceptions.ApplicationException;
import com.google.common.collect.Lists;
import org.springframework.http.HttpStatus;


public class HashMapAnswersDao implements AnswersDao {
    private Map<String, Answer> answers = new HashMap<>();

    public HashMapAnswersDao() {

        answers.put("1", new Answer("1", "Java 8 features : lambdas,filters,streams",
            "1", Lists.newArrayList("java", "lambdas")));
        answers.put("2", new Answer("2", "Java 8 features : optionals",
            "1", Lists.newArrayList("java", "lambdas")));
        answers.put("3", new Answer("3", "Java 8 features : completable futures",
            "1", Lists.newArrayList("java", "lambdas")));


        answers.put("4", new Answer("4", "spring.io, youtube",
            "2", Lists.newArrayList("spring", "micro-services")));
        answers.put("5", new Answer("5", "baeldung.com",
            "2", Lists.newArrayList("spring", "micro-services")));

    }


    @Override
    public void save(Answer input) {
        if (input.getId() != null) {
            Answer saved = answers.get(input.getId());
            if (saved != null) {
                if (input.getAnswer() != null)
                    saved.setAnswer(input.getAnswer());
                if (input.getTags() != null)
                    saved.setTags(input.getTags());

            }
            return;
        }
        final String id = answers.keySet().size() + 1 + "";
        input.setId(id);
        answers.put(id, input);
    }

    @Override
    public Answer get(String answerId) {
        return Optional.ofNullable(answers.get(answerId))
            .orElseThrow(() ->
                new ApplicationException("Answer not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Answer> getAllAnswersFor(String questionId) {
        return answers.values()
            .stream()
            .filter(answer -> answer.getQuestionId().equals(questionId))
            .collect(Collectors.toList());
    }
}
