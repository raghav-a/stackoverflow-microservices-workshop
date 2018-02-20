package com.agilefaqs.stackoverflow.questions.dao;

import com.agilefaqs.stackoverflow.questions.model.Question;
import com.agilefaqs.stackoverflow.exceptions.ApplicationException;
import com.google.common.collect.Lists;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HashMapQuestionsDao implements QuestionsDao {

    private Map<String, Question> questions = new HashMap<>();

    public HashMapQuestionsDao() {
        questions.put("1", new Question("1", "New features in java 8", "Can some one share the new features in java 8 and also some resources to learn them",
            Lists.newArrayList("java")));
        questions.put("2", new Question("2", "Good sites for leaning spring boot", "Can some one share some good sites to lean spring boot in detail.", Lists.newArrayList("spring", "microservices")));
        questions.put("3", new Question("3", "Set principal in servlet filter", "I want to populate Principal object from the data passed in the headers of http request. How to do this in a servlet filter?", Lists.newArrayList("java", "servlets")));
    }


    @Override
    public Question get(String questionId) {
        return Optional.ofNullable(questions.get(questionId))
            .orElseThrow(() ->
                new ApplicationException("Question not found", HttpStatus.NOT_FOUND));

    }

    @Override
    public void save(Question input) {
        if (input.getId() != null) {
            Question saved = questions.get(input.getId());
            if (saved != null) {
                saved.update(input);
            } else
                throw new ApplicationException("Question not found", HttpStatus.NOT_FOUND);
            return;
        }
        input.setId(generateID());
        questions.put(input.getId(), input);

    }

    private String generateID() {
        return String.valueOf(questions.keySet().size() + 1);
    }
}
