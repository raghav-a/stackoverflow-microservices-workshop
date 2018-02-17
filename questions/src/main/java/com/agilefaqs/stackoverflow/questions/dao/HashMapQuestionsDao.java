package com.agilefaqs.stackoverflow.questions.dao;

import com.agilefaqs.stackoverflow.questions.model.Question;
import com.agilefaqs.stackoverflow.exceptions.ApplicationException;
import com.google.common.collect.Lists;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class HashMapQuestionsDao implements QuestionsDao {

    private Map<String, Question> questions = new HashMap<>();

    public HashMapQuestionsDao() {
        questions.put("1", new Question("1", "what are the new features in java 8", Lists.newArrayList("java")));
        questions.put("2", new Question("2", "Can anyone share some good sites for leaning spring boot", Lists.newArrayList("spring", "microservices")));
    }


    @Override
    public Question get(String questionId) {
        return questions.get(questionId);
    }

    @Override
    public void save(Question input) {
        if (input.getId() != null) {
            Question saved = questions.get(input.getId());
            if (saved != null) {
                saved.update(input);
            }
            else
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
